package ClientSide_Demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import java.lang.reflect.Type;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import GameGUI.*;
import Protocol.*;

public class ListeningThread extends Thread {

    private Socket clientSocket;
    private BufferedReader in;
    private BufferedWriter out;
    private Gson gson;
    private boolean flag;

    private String username;

    
	String[] list;
	JList<String> wait_list;
	WaitListGUI wl;
	GameWindow gw;
	
    public ListeningThread(Socket clientSocket,String username) throws UnsupportedEncodingException, IOException {
        this.clientSocket = clientSocket;
        in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream(), "UTF-8"));
        out=new BufferedWriter(new OutputStreamWriter(this.clientSocket.getOutputStream(),"UTF-8"));
        gson = new Gson();
        wl=new WaitListGUI(out, username);
        gw = new GameWindow(out, username);

		this.username = username;
        flag=true;
    }
    
    public ListeningThread() {}

    public void run() {
        try {
            while (flag) {
                String str = in.readLine();
                System.out.println(str);
                Packet<?> rawPacket = gson.fromJson(str, Packet.class);
                String header = rawPacket.getHeader();
                System.out.println("#RECEIVED: "+header);
                if (header.equals("Reply")) {
                    Type type = new TypeToken<Packet<Reply>>() {}.getType();
                    Packet<Reply> inPacket = gson.fromJson(str, type);
                    System.out.println(inPacket.getContent().getType());
                    System.out.println(inPacket.getContent().getResult());
                    System.out.println(inPacket.getContent().getMessage());
                    if(!inPacket.getContent().getResult()) {
                    	Boolean status=true;
                    	while(status) {

                    		String s = JOptionPane.showInputDialog("Oops! Username is already taken or invalid. Please enter a different username");
                    		if(s==null) {
                    			closeSocket();
                    			status = false;
                    		}
                    		else if(s != null && !s.contains(" ") && s.matches("[A-Za-z0-9]+")) {
            			        Packet<Login> outPacket = new Packet<Login>("Login", new Login(s),s);
            			        out.write(gson.toJson(outPacket) + "\n");
            			        out.flush();
            			        this.username = s;
            			        status = false;
                    		}
                    		else {
                    			JOptionPane.showMessageDialog(new JFrame(), "Please enter a valid username. A valid username contains only alphabets and numbers. It should be a minimum of length 1.","Error", 1);
                    		}
                    	

                    			
                    	}
                    }
                    else if(inPacket.getUsername().equals("Login"))
                    	wl.waitGUI();
                    if(inPacket.getContent().getMessage()!=null && inPacket.getContent().getMessage().equals("Yes")) {
                    	wl.disableCreateButton();
                    	System.out.println("Disabling Create Button for:"+username);
                    }
                    else {
                    	wl.enableCreateButton();
                    }
                }
				
				else if(header.equals("WaitingList")) {
                	Type type=new TypeToken<Packet<WaitingList>>() {}.getType();
                	Packet<WaitingList> inPacket=gson.fromJson(str, type);
                	if(inPacket.getContent()!=null) {
                		list=inPacket.getContent().getList();
                		System.out.println("Sending List to WaitList: "+ list);
                		wl.updateWlGUI(list);
                		gw.updateGwGUI(list);
                	}
                	else {
                		list=new String[1];
                		list[0]="";
                		wl.updateWlGUI(list);
                		gw.updateGwGUI(list);
                	}
                	
                	if(inPacket.getContent()!=null && inPacket.getContent().gameStatus().equals("Yes")) //Checking if Game has started to disable/enable the create button
                		wl.disableCreateButton();
                	else
                		wl.enableCreateButton();
                	
                	
                }
                
				else if(header.equals("GameList")) {
                	Type type=new TypeToken<Packet<GameList>>() {}.getType();
                	Packet<GameList> inPacket=gson.fromJson(str, type);
                	list=inPacket.getContent().getList();
                	System.out.println("Sending GameList: "+ list);
                	gw.gameGUI();
                	gw.updateGameList(list);			// sending the game players list to the game window
                }
                
				else if(header.equals("EndGame")) {
					System.out.println("Ending Game for and starting Waiting List:"+username);
					wl.waitGUI();
					System.out.println("Enabling Create Button");
					wl.enableCreateButton();
					System.out.println("Close Game GUI");
					wl.disbleJoinButton();
					
					gw.closeGameGUI();
					gw=new GameWindow(out, this.username);
					
				}
                
				else if(header.equals("Logout")) {
					closeSocket();
				}
				else if(header.equals("Invitation")) {
					Type type=new TypeToken<Packet<Invitation>>() {}.getType();
					Packet<Invitation> inPacket = gson.fromJson(str, type);
					JOptionPane.showMessageDialog(new JFrame(), "You have been invited by "+inPacket.getContent().from()+". Please click on Join Button to enter the game.", "Invitation", JOptionPane.INFORMATION_MESSAGE);
					wl.enableJoinButton();
				}
				else if(header.equals("JoinGame")) {			//Invite Button Disabled for player who joins an existing game
					gw.disableInviteButton();
				}
				else if(header.equals("Insert")) {
					Type type=new TypeToken<Packet<Insert>>() {}.getType();
					Packet<Insert> inPacket = gson.fromJson(str, type);
					gw.updateTable(inPacket);
				}
				else if(header.equals("passChance")) {							//Passing the chance of the Player
					Type type=new TypeToken<Packet<Reply>>() {}.getType();
					Packet<Reply> inPacket = gson.fromJson(str, type);
					gw.updateChance(Integer.parseInt(inPacket.getContent().getType()));
				}
				else if(header.equals("StartGame")) {
					gw.startGame();
				}
				else if(header.equals("Vote")) {
					Type type=new TypeToken<Packet<Reply>>() {}.getType();
					Packet<Reply> inPacket = gson.fromJson(str, type);
					
					if (JOptionPane.showConfirmDialog(new JFrame(), "Do you agree that "+ inPacket.getContent().getMessage() +" is a word?" , this.username+"'s Vote", JOptionPane.YES_NO_OPTION,
				            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
						Packet<Vote> outPacket=new Packet<Vote>("Vote", new Vote(true), inPacket.getUsername());
						out.write(gson.toJson(outPacket)+ "\n");
						out.flush();
				        }
					else
					{
						Packet<Vote> outPacket=new Packet<Vote>("Vote", new Vote(false), inPacket.getUsername());
						out.write(gson.toJson(outPacket)+ "\n");
						out.flush();
					}
					
				}
				else if(header.equals("VoteResult")) {
					Type type=new TypeToken<Packet<Reply>>() {}.getType();
					Packet<Reply> inPacket = gson.fromJson(str, type);
					if(inPacket.getContent().getResult())
						JOptionPane.showMessageDialog(new JFrame(), "Your word has been approved", "Vote Results", JOptionPane.INFORMATION_MESSAGE);
					else
						JOptionPane.showMessageDialog(new JFrame(), "Your word has been rejected ", "Vote Results", JOptionPane.INFORMATION_MESSAGE);
				}
            }
        } catch (Exception e) {
            if (flag) {
                System.out.println("Connection Stream aborts unexpectedly.\n");
                JOptionPane.showMessageDialog(new JFrame(), "Server has disconnected", "Error", JOptionPane.ERROR_MESSAGE);
                try {
					closeSocket();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                e.printStackTrace();
                flag = false;
            }
        }
    }

    public void closeSocket() throws IOException {
        flag = false;
        clientSocket.close();
    }

}
