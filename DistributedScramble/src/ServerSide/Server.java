package ServerSide;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import Protocol.WaitingList;

public class Server {

    private int port;
    private ConcurrentHashMap<String, ServeClientThread> threadMap;
    private ServerSocket listeningSocket;
    private ArrayList<String> waitingList;

    public Server(int port) throws IOException {
        listeningSocket = new ServerSocket(port);
        threadMap = new ConcurrentHashMap<String, ServeClientThread>();
        this.port = port;
        waitingList = new ArrayList<String>();
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
                ServeClientThread t = new ServeClientThread(clientSocket, this);
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
            t.send(new Packet<WaitingList>("WaitingList", new WaitingList(list)));
        }
    }

    public void register(String username, ServeClientThread thread) {
        threadMap.put(username, thread);
        waitingList.add(username);
    }

    public static void main(String[] args) {
        int port = 3000;
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