package ServerSide;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import Protocol.*;

public class ServeClientThread extends Thread {

    private Socket clientSocket;
    private BufferedReader in;
    private BufferedWriter out;
    private Gson gson;
    private Server server;

    public ServeClientThread(Socket clientSocket, Server server) throws UnsupportedEncodingException, IOException {
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream(), "UTF-8"));
        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream(), "UTF-8"));
        this.clientSocket = clientSocket;
        gson = new Gson();
        this.server = server;
    }

    public void run() {
        String clientJson = null;
        try {
            while ((clientJson = in.readLine()) != null) {
                System.out.println(clientJson);
                Packet<?> inPacket = gson.fromJson(clientJson, Packet.class);
                String header = inPacket.getHeader();
                System.out.println(header);
                switch (header) 
                {
                    case "Login": {
                        Packet<Login> loginPacket = gson.fromJson(clientJson, new TypeToken<Packet<Login>>() {}.getType());
                        server.register(loginPacket.getContent().getUsername(), this);
                        Reply reply = new Reply("Login", true, null);
                        Packet<Reply> outPacket= new Packet<Reply>("Reply", reply);
                        out.write(gson.toJson(outPacket) + "\n");
                        out.flush();
                        server.broadcastWaitingList();
                        break;
                    }
                    case "CreateGame": {
                        Reply reply = new Reply("CreateGame", true, null);
                        Packet<Reply> outPacket= new Packet<Reply>("Reply", reply);
                        out.write(gson.toJson(outPacket) + "\n");
                        out.flush();
                        String[] list = {"dalao"};
                        server.broadcast(new Packet<GameList>("GameList", new GameList(list)));
                        break;
                    }
                    case "Invite": {
                        Reply reply = new Reply("Invite", true, null);
                        Packet<Reply> outPacket= new Packet<Reply>("Reply", reply);
                        out.write(gson.toJson(outPacket) + "\n");
                        out.flush();
                        server.broadcast(new Packet<Invitation>("Invitation", new Invitation("dalao")));
                        break;
                    }
                    case "JoinGame": {
                        Reply reply = new Reply("JoinGame", true, null);
                        Packet<Reply> outPacket= new Packet<Reply>("Reply", reply);
                        out.write(gson.toJson(outPacket) + "\n");
                        out.flush();
                        String[] list = {"dalao"};
                        server.broadcast(new Packet<GameList>("GameList", new GameList(list)));
                        break;
                    }
                    case "StartGame": {
                        Reply reply = new Reply("StartGame", true, null);
                        Packet<Reply> outPacket= new Packet<Reply>("Reply", reply);
                        out.write(gson.toJson(outPacket) + "\n");
                        out.flush();
                        String[] list = {"dalao"};
                        server.broadcast(new Packet<GameList>("GameList", new GameList(list)));
                        break;
                    }
                    case "Insert": {
    
                    }
                    case "Highlight": {
    
                    }
                    case "vote": {
    
                    }
                }
            }
        } catch (UnsupportedEncodingException e) {
            System.out.println("The Character Encoding is not supported.");
        } catch (IOException e) {
            System.out.println("IO Issues happened.");
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void send(Packet<?> packet) {
        try {
            out.write(gson.toJson(packet) + "\n");
            out.flush();
        } catch (IOException e) {
            System.out.println("IO Issues happened.");
        }
    }
}
