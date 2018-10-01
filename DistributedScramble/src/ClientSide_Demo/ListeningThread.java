package ClientSide_Demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
    private Gson gson;
    private boolean flag;
    
	String[] list;
	JList<String> wait_list;
	WaitListGUI wl;
	GameWindow gw;
	
    public ListeningThread(Socket clientSocket) throws UnsupportedEncodingException, IOException {
        this.clientSocket = clientSocket;
        in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream(), "UTF-8"));
        gson = new Gson();
        wl=new WaitListGUI();
        wl.waitGUI();
        flag = true;
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
                }
				
				else if(header.equals("WaitingList")) {
                	Type type=new TypeToken<Packet<WaitingList>>() {}.getType();
                	Packet<WaitingList> inPacket=gson.fromJson(str, type);
                	list=inPacket.getContent().getList();
                	System.out.println("Sending List to WaitList: "+ list);
                	wl.updateWlGUI(list);
                }
                
				else if(header.equals("GameList")) {
                	Type type=new TypeToken<Packet<GameList>>() {}.getType();
                	Packet<GameList> inPacket=gson.fromJson(str, type);
                	list=inPacket.getContent().getList();
                	System.out.println("Sending List to GameList: "+ list);
                	gw.updateGwGUI(list);
                }
            }
        } catch (Exception e) {
            if (flag) {
                System.out.println("Connection Stream aborts unexpectedly.\n");
                flag = false;
            }
        }
    }

    public void closeSocket() throws IOException {
        flag = false;
        clientSocket.close();
    }

}
