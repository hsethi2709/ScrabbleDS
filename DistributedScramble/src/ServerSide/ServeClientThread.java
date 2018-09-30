/******************************************************************************
 *  Dependencies: gson-2.8.5.jar (third-party library) 
 *                Protocol Package
 *
 *  The Class dedicated to serve every single players, including data transfer function
 *  and judgment statements for the validation of user input.
 *  
 ******************************************************************************/

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

    private Socket clientSocket;    // the socket between served client and server
    private BufferedReader in;
    private BufferedWriter out;
    private Gson gson;              // instance of Json parser
    private Server server;
    private String username;

    /**
     * The constructor of ServeClientThread. Takes client socket and server instance as parameters.
     * Client socket is needed to initialize input and output streams.
     * Server object is used for broadcasting.
     * 
     * @param clientSocket  The socket between served client and server
     * @param server        The instance object of Server Class
     * @throws UnsupportedEncodingException     
     *                      throws when constructing StreamWriter instances, if the encoding pattern is not supported.
     * @throws IOException  throws when getting input and output stream if clientSocket resets unexpectedly. 
     */
    
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
            // The thread keeps listening to client and receives packets from client by clientJson object
            while ((clientJson = in.readLine()) != null) {
                System.out.println("#Received: " + clientJson); // testing code
                // when the type of incoming Json file is not clear, convert it by raw type and read its header.
                Packet<?> inPacket = gson.fromJson(clientJson, Packet.class);   
                String header = inPacket.getHeader();
                System.out.println("#Header: " + header); // testing code
                // condition statements to judge header and take specific actions.
                switch (header) 
                {
                    case "Login": {
                        Packet<Login> loginPacket = gson.fromJson(clientJson, new TypeToken<Packet<Login>>() {}.getType());
                        this.username = loginPacket.getContent().getUsername();
                        server.registerToWaiting(this.username, this);
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
                        String name = this.username;
                        server.registerToGame(name);
                        break;
                    }
                    case "Invite": {
                        Reply reply = new Reply("Invite", true, null);
                        Packet<Reply> outPacket= new Packet<Reply>("Reply", reply);
                        out.write(gson.toJson(outPacket) + "\n");
                        out.flush();
                        break;
                    }
                    case "JoinGame": {
                        Reply reply = new Reply("JoinGame", true, null);
                        Packet<Reply> outPacket= new Packet<Reply>("Reply", reply);
                        out.write(gson.toJson(outPacket) + "\n");
                        out.flush();
                        server.registerToGame(this.username);
                        server.broadcastGameList();
                        break;
                    }
                    case "StartGame": {
                        Reply reply = new Reply("StartGame", true, null);
                        Packet<Reply> outPacket= new Packet<Reply>("Reply", reply);
                        out.write(gson.toJson(outPacket) + "\n");
                        out.flush();
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
            System.out.println("The clinet Socket resets unexpectedly.");
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.out.println("The clinet Socket cannot be closed correctly.");
            }
        }
    }

    public void send(Packet<?> packet) {
        try {
            out.write(gson.toJson(packet) + "\n");
            out.flush();
        } catch (IOException e) {
            System.out.println("The clinet Socket resets unexpectedly.");
        }
    }
}
