package ClientSide_Demo;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import com.google.gson.Gson;

import Protocol.*;

public class Client {
    private static String ip = "localhost";
    private static int port = 3000;
    private static Gson gson = new Gson();

    public static void main(String[] args) throws UnknownHostException, IOException, InterruptedException {
        try (Socket socket = new Socket(ip, port);) {
            ListeningThread listeningThread = new ListeningThread(socket);
            listeningThread.start();
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"));
            Packet<Login> outPacket = new Packet<Login>("Login", new Login("Harshit"));
            out.write(gson.toJson(outPacket) + "\n");
            out.flush();
            Thread.sleep(1000);
            listeningThread.close();
            socket.close();
        }

    }
}
