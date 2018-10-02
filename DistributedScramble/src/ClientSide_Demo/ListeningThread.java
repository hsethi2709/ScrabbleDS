package ClientSide_Demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

import java.lang.reflect.Type;
import java.net.Socket;
import javax.swing.JList;

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
    @SuppressWarnings("unused")
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
        wl.waitGUI();		 
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
                System.out.println(header);
                if (header.equals("Reply")) {
                    Type type = new TypeToken<Packet<Reply>>() {}.getType();
                    Packet<Reply> inPacket = gson.fromJson(str, type);
                    System.out.println(inPacket.getContent().getType());
                    System.out.println(inPacket.getContent().getResult());
                    System.out.println(inPacket.getContent().getMessage());
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
					gw.closeGameGUI();
					
				}
                
				else if(header.equals("Logout")) {
					closeSocket();
				}
				else if(header.equals("Invitation")) {
					wl.enableJoinButton();
				}
				else if(header.equals("JoinGame")) {
					gw.disableInviteButton();
				}
            }
        } catch (Exception e) {
            if (flag) {
                System.out.println("Connection Stream aborts unexpectedly.\n");
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
