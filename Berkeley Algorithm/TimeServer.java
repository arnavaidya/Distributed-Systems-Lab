import java.io.*;
import java.net.*;
import java.util.*;

public class TimeServer {
    public static void main(String[] args) throws Exception {
        int port = 5000;
        ServerSocket serverSocket = new ServerSocket(port);
        List<Socket> clients = new ArrayList<>();
        List<Long> clientTimes = new ArrayList<>();

        System.out.println("Time Server started ...\n");
        while (clients.size() < 1)
        {
            Socket socket = serverSocket.accept();
            clients.add(socket);
            System.out.println("Connected to " + socket);
        }

        //Get times from clients
        for(Socket s : clients)
        {
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            DataInputStream in = new DataInputStream(s.getInputStream());
            out.writeUTF("SEND_TIME");
            long clientTime = in.readLong();
            clientTimes.add(clientTime);
        }
        long serverTime = System.currentTimeMillis();
        long sum = serverTime;
        for(long t : clientTimes) {
            sum += t;
        }
        long avgTime = sum / (clientTimes.size() + 1);
        for(int i = 0; i<clients.size(); i++) {
            Socket s = clients.get(i);
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            long offset = avgTime - clientTimes.get(i);
            out.writeLong(offset);
        }
        serverSocket.close();
    }
}
