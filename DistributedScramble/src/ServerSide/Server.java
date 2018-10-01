/******************************************************************************
 *  Dependencies: gson-2.8.5.jar (third-party library) 
 *                Protocol Package
 *                ServeClientThread.java
 *
 *  The main thread of ServerSide programs. 
 *  It initializes Server socket, keeps listening to client's connection and creates threads for each incoming connection.
 *  It also implements several broadcasting functions like broadcasting waitingList.
 *  It keeps clientThreads by ConcurrentHashMap. It also keeps waitingList and gameList.
 *  
 ******************************************************************************/

package ServerSide;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import Protocol.*;

public class Server {

    private int port;   // Server listening port, parsed by cmdLineArgs.Class
    private ConcurrentHashMap<String, ServeClientThread> threadMap; // HashMap takes player's username and corresponding Thread as key value pair.
    private ServerSocket listeningSocket;   // The server socket bounded to specified port indicated above.
    private List<String> waitingList;  // The list keeps usernames of players who are waiting to play.
    private List<String> gameList;  // The list keeps usernames of players who are waiting to play.
    private String gameStarted;
    
    public Server(int port) throws IOException {
        this.port = port;
        listeningSocket = new ServerSocket(port);
        threadMap = new ConcurrentHashMap<String, ServeClientThread>();
        waitingList = Collections.synchronizedList(new ArrayList<String>());
        gameList = Collections.synchronizedList(new ArrayList<String>());
        gameStarted="No";
    }

    public void execute(Scanner scanner, ExecutorService executor) {
        try {
            System.out.println("Server listening on port " + port + " for a connection");
            int count = 0;
            
            while (true) {
                Socket clientSocket = listeningSocket.accept();
                count++;
                System.out.println("Client connection number " + count + " accepted:");
                System.out.println("Remote Port: " + clientSocket.getPort());
                System.out.println("Remote Hostname: " + clientSocket.getInetAddress().getHostName());
                System.out.println("Local Port: " + clientSocket.getLocalPort());
                ServeClientThread t = new ServeClientThread(clientSocket, this,gameStarted);
                executor.submit(t);
            }
        } catch (UnsupportedEncodingException e) {
            System.out.println("The Character Encoding is not supported.");
        } catch (IOException e) {
            System.out.println("IO Issues happened.");
        } finally {
            if (listeningSocket != null) {
                try {
                    listeningSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void broadcast(Packet<?> packet) {
        for (ServeClientThread t : threadMap.values()) {
            t.send(packet);
        }
    }

    public void broadcastWaitingList() {
        String[] list = waitingList.toArray(new String[0]);

        for (ServeClientThread t : threadMap.values()) {
            t.send(new Packet<WaitingList>("WaitingList", new WaitingList(list),"Server"));
        }
    }
    
    public void broadcastGameList() {
        String[] list = gameList.toArray(new String[0]);
        
        //for (ServeClientThread t : threadMap.values()) {
        for (String name: gameList ) {
        	ServeClientThread t=threadMap.get(name);
            t.send(new Packet<GameList>("GameList", new GameList(list),"Server"));
        }
    }

    public synchronized void registerToWaiting(String username, ServeClientThread thread) {
        threadMap.put(username, thread);
        waitingList.add(username);
    }
    
    public synchronized void registerToGame(String username) {
        gameList.add(username);
    }
    
    public void updateGameStatus(String status)
    {
    	gameStarted=status;
    }

    public static void main(String[] args) {
        int port = 0;
        CmdLineArgs argsBean = new CmdLineArgs();
        CmdLineParser parser = new CmdLineParser(argsBean);
        try {
            parser.parseArgument(args);
            port = argsBean.getPort();
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            parser.printUsage(System.err);
            System.exit(1);
        }

        Scanner scanner = new Scanner(System.in);
        ExecutorService executor = Executors.newFixedThreadPool(100);
        try {
            Server server = new Server(port);
            server.execute(scanner, executor);
        } catch (IOException e) {
            System.out.println("Server Socket cannot be created");
        }
    }
}
