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
    
	String[] list;
	JList<String> wait_list;
	WaitListGUI wl;
	GameWindow gw;
	
    public ListeningThread(Socket clientSocket) throws UnsupportedEncodingException, IOException {
        this.clientSocket = clientSocket;
        in = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream(), "UTF-8"));
        out=new BufferedWriter(new OutputStreamWriter(this.clientSocket.getOutputStream(),"UTF-8"));
        gson = new Gson();
        wl=new WaitListGUI(out);
        gw = new GameWindow();
		
        wl.main("");							 
        flag=true;
    }

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
            }
        } catch (Exception e) {
            if (flag) {
                System.out.println("Connection Stream aborts unexpectedly.\n");
                flag = false;
            }
        }
    }

    public void close() throws IOException {
        flag = false;
        clientSocket.close();
    }

}
