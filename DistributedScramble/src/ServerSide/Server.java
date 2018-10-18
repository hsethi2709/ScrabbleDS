/******************************************************************************
 *  Dependencies: gson-2.8.5.jar (third-party library) 
 *                Protocol Package
 *                ServeClientThread.java
 *
 *  The main thread on Server side of Scrabble Game System . 
 *  It initializes Server socket, keeps listening to client's connection and creates threads for each incoming connection.
 *  It also implements several broadcasting functions.
 *  It keeps system relevant data structures such as clientThreads by ConcurrentHashMap.
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
    private List<String> waitingList;  // The list keeps usernames of players who are waiting in waiting room.
    private List<String> gameList;  // The list keeps usernames of players who are waiting in game room.
    private String gameCreated;
    private int chance;
    private int passCount;

    @SuppressWarnings("unused")
	private String gameStarted;

    private int voteCount;
    private int voteResults;

    /**
     * The Server Class takes a port number as parameter.
     * It starts a ServerSocket bounds to that port and keeps listening to client connection.
     * 
     * @param port          The port number Server keeps listening to.
     * @throws IOException  throws IOException when ServerSocket cannot be created.
     */
    public Server(int port) throws IOException {
        this.port = port;
        listeningSocket = new ServerSocket(port);
        threadMap = new ConcurrentHashMap<String, ServeClientThread>();
        waitingList = Collections.synchronizedList(new ArrayList<String>());
        gameList = Collections.synchronizedList(new ArrayList<String>());
        gameCreated = "No";
        this.chance = 0;
        this.passCount = 0;
    }
    
    /**
     * ServerSocket starts to keep listening on given port number. 
     * Once a clientSocket comes in, prints out its brief information, 
     * creates a corresponding ServeClientThread to serve client and submit it to ThreadPool.
     * 
     * @param scanner   the keyboard scanner on server side, used to input monitoring commands.
     * @param executor  the ThreadPool to which ServeClientThreads are submitted.
     */
    public void execute(Scanner scanner, ExecutorService executor) {
        try {
            System.out.println("Server listening on port " + port + " for a connection");
            int count = 0;
            
            // ControlThread controlThread = new ControlThread(scanner, this, this.listeningSocket, executor);
            // controlThread.start();
            while (true) {
                Socket clientSocket = listeningSocket.accept();
                count++;
                System.out.println("Client connection number " + count + " accepted:");
                System.out.println("Remote Port: " + clientSocket.getPort());
                System.out.println("Remote Hostname: " + clientSocket.getInetAddress().getHostName());
                System.out.println("Local Port: " + clientSocket.getLocalPort());
                ServeClientThread t = new ServeClientThread(clientSocket, this, gameCreated);
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

    /**
     * Broadcasting a message to all clients have logged in.
     * 
     * @param packet    the Packet object to be broadcast
     */
    public void broadcast(Packet<?> packet) {
        for (ServeClientThread t : threadMap.values()) {
            t.send(packet);
        }
    }

    /**
     * Broadcasting username list waiting in waiting room.
     * 
     */
    public void broadcastWaitingList() {
        String[] list = waitingList.toArray(new String[0]);

        if (list.length != 0) {
            for (ServeClientThread t : threadMap.values())
                t.send(new Packet<WaitingList>("WaitingList", new WaitingList(list, gameCreated), "Server"));
        } else {
            if (!threadMap.isEmpty()) {
                for (ServeClientThread t : threadMap.values())
                    t.send(new Packet<WaitingList>("WaitingList", null, "Server"));
            }
        }
    }
    
    /**
     * Broadcasting username list waiting in game room.
     * 
     */
    public void broadcastGameList() {
        String[] list = gameList.toArray(new String[0]);

        for (String name : gameList) {
            ServeClientThread t = threadMap.get(name);
            t.send(new Packet<GameList>("GameList", new GameList(list), "Server"));
        }
    }
    
    /**
     * Send Invitation message to players are invited.
     * 
     * @param invitation_list   the list of invited users' username.
     * @param invitee   the username of player who initiates invitation request.
     */
    public void invitePlayers(String[] invitation_list, String invitee) {
        for (String name : invitation_list) {
            threadMap.get(name).send(new Packet<Invitation>("Invitation", new Invitation(invitee), "Server"));
        }
    }

    /**
     * Attempts to register a username on Server, returns registration result in boolean type.
     * 
     * @param username  the username to be registered
     * @param thread    the corresponding ServeClientThread
     * @return  true if registered successfully;
     *          false if username has existed or username is illegal.
     */
    public synchronized Boolean registerToWaiting(String username, ServeClientThread thread) {
        if (threadMap.containsKey(username) || !isUsernameValidate(username)) {
            return false;
        } else {
            threadMap.put(username, thread);
            waitingList.add(username);
            return true;
        }
    }
    
    private boolean isUsernameValidate(String username) {
        return username != null && !username.contains(" ") && username.matches("[A-Za-z0-9]+");
    }
    
    /**
     * Register a username from waiting list to game list.
     * 
     * @param username  the username to be registered
     */
    public synchronized void registerToGame(String username) {
        gameList.add(username);
        waitingList.remove(username);

    }
    
    /**
     * Broadcasting highlighted word to players in game.
     * 
     * @param packet    the packet object containing the word
     */
    public void broadcastWord(Packet<Insert> packet) {
        for (String name : gameList) {
            ServeClientThread t = threadMap.get(name);
            t.send(packet);
        }
    }
    
    /**
     * Send end game message to all players in the game.
     * 
     */
    public synchronized void endGame() {
        System.out.println("GameList Size:" + gameList.size());
        for (ServeClientThread t : threadMap.values()) {
            
            System.out.println("Sending End Game Packet to:" + t.getName());
            t.send(new Packet<GameList>("EndGame", null, "Server"));
            System.out.print("Sent End Game Packet to:" + t.getName());
            if(!waitingList.contains(t.getUsername()))
            	waitingList.add(t.getUsername());
        }
        updateGameStatus("No");
        broadcastWaitingList();
        gameList.clear();
    }
    
    /**
     * When a user logs out, we remove its username from waiting together with corresponding entry in threadMap,
     * then we broadcast the update of game list to all players.
     * 
     * @param username  the username of user logged out
     */
    public void logOut(String username) {
        waitingList.remove(username);
        threadMap.get(username).send(new Packet<GameList>("Logout", null, "Server"));
        threadMap.remove(username);
        broadcastWaitingList();
    }
    
    /**
     * Update the status of the game.
     * @param status    the status of game, 'Yes' means already exists, 'No' means not.
     */
    public void updateGameStatus(String status) {
        this.gameCreated = status;
    }
    public void resetChance()
    {
    	this.chance=0;
    }
    
    public void callVote(String username,String word) {
    	for (String name: gameList ) {
    		
    		if(!name.trim().equals(username.trim())) {
    			System.out.print("CallVote: "+ name +" " +username);
        	ServeClientThread t=threadMap.get(name);
            t.send(new Packet<Reply>("Vote", new Reply(username, false, word) ,username));
    		}
    	}
    }
    
    public void countVote(String username,Boolean result,String word) {
    	this.voteCount+=1;
    	System.out.println("Vote Count: "+voteCount);
    	
    	if(result==true) 
    		this.voteResults+=1;
    	System.out.println("VoteResult "+voteResults);
    	if(this.voteCount==gameList.size()-1) {
    		ServeClientThread t=threadMap.get(username);
    		if(this.voteResults==gameList.size()-1) {
    			for (ServeClientThread t1: threadMap.values()) {
    					t1.send(new Packet<Reply>("VoteResult",new Reply(null,true,word),username));
    			}
    			//Send Vote Approved Message
    		}
    		else {
    			
    			t.send(new Packet<Reply>("VoteResult",new Reply(null,false,word),username));
    			//Send Vote Disapproved Message
    		}
    		this.voteCount=0;
    		this.voteResults=0;
    	}
    	
    }
    
    
    
    public void nextChance(int i) {
        if (i == 1)
            this.passCount += 1;
        else
            this.passCount = 0;

        if (this.passCount == gameList.size())
            endGame();
        else {
            if (this.chance == gameList.size())
                this.chance = 0;

            String username = gameList.get(chance);
            for (ServeClientThread t : threadMap.values())
                t.send(new Packet<Reply>("passChance", new Reply(Integer.toString(this.chance), true, null), username));
            this.chance += 1;
        }
    }
    
    /*
    public void stopAllThreads() {
        for (ServeClientThread t : threadMap.values()) {
            t.close();
        }
    }
    */
    

    public static void main(String[] args) {
        int port = 0; // default port number

        // Receives listening port number from command line
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

        final int NUMBER_OF_THREADS_IN_POOL = 100;
        Scanner scanner = new Scanner(System.in);
        ExecutorService executor = Executors.newFixedThreadPool(NUMBER_OF_THREADS_IN_POOL);
        try {
            Server server = new Server(port);
            server.execute(scanner, executor);
        } catch (IOException e) {
            System.out.println("Server Socket cannot be created");
        }
    }
}



