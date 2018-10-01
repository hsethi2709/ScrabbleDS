package ClientSide_Demo;

import java.io.BufferedReader;
<<<<<<< HEAD
import java.io.BufferedWriter;
=======

>>>>>>> refs/remotes/origin/harshit
import java.io.IOException;
import java.io.InputStreamReader;
<<<<<<< HEAD
import java.io.OutputStreamWriter;
=======

>>>>>>> refs/remotes/origin/harshit
import java.io.UnsupportedEncodingException;

import java.lang.reflect.Type;
import java.net.Socket;
import javax.swing.JList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

<<<<<<< HEAD
import GameGUI.*;
=======
import GameGUI.WaitListGUI;
>>>>>>> refs/remotes/origin/harshit
import Protocol.*;

public class ListeningThread extends Thread {

    private Socket clientSocket;
    private BufferedReader in;
<<<<<<< HEAD
    private BufferedWriter out;
=======
    
>>>>>>> refs/remotes/origin/harshit
    private Gson gson;
    private boolean flag;
<<<<<<< HEAD
    
	String[] list;
	JList<String> wait_list;
	WaitListGUI wl;
	GameWindow gw;
	
=======
	String[] list;
	JList<String> wait_list;
	WaitListGUI wl;
>>>>>>> refs/remotes/origin/harshit
    public ListeningThread(Socket clientSocket) throws UnsupportedEncodingException, IOException {
        this.clientSocket = clientSocket;
        in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream(), "UTF-8"));
<<<<<<< HEAD
        out=new BufferedWriter(new OutputStreamWriter(this.clientSocket.getOutputStream(),"UTF-8"));
=======
        
>>>>>>> refs/remotes/origin/harshit
        gson = new Gson();
<<<<<<< HEAD
        wl=new WaitListGUI(out);
        gw = new GameWindow();
		
        wl.main("");							 
        flag=true;
=======
        wl=new WaitListGUI();
        wl.main("");
        flag = true;
>>>>>>> refs/remotes/origin/harshit
    }
    
    
    
    /*public ListeningThread(JList<String> jList)
    {
    	wait_list=jList;
    }*/

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
                    if(inPacket.getContent().getMessage()!=null && inPacket.getContent().getMessage().equals("Yes"))
                    	wl.disableCreateButton();
                }
				
				else if(header.equals("WaitingList")) {
                	Type type=new TypeToken<Packet<WaitingList>>() {}.getType();
                	Packet<WaitingList> inPacket=gson.fromJson(str, type);
                	list=inPacket.getContent().getList();
                	System.out.println("Sending List to WaitList: "+ list);
                	wl.updateWlGUI(list);
                	gw.updateGwGUI(list);
                }
                
				else if(header.equals("GameList")) {
                	Type type=new TypeToken<Packet<GameList>>() {}.getType();
                	Packet<GameList> inPacket=gson.fromJson(str, type);
                	list=inPacket.getContent().getList();
                	System.out.println("Sending List to GameList: "+ list);
                	gw.main(null);
                	wl.disableCreateButton();
                	
                	
                	
                	
                }
                else if(header.equals("WaitingList"))
                {
                	
                	Type type=new TypeToken<Packet<WaitingList>>() {}.getType();
                	Packet<WaitingList> inPacket=gson.fromJson(str, type);
                	list=inPacket.getContent().getList();
                	System.out.println("Sending List: "+ list);
                	wl.updateGUI(list);
                	
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

    public void close() throws IOException {
        flag = false;
        clientSocket.close();
    }


    

}
