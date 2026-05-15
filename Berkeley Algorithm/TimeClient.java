import java.io.*;
import java.util.*;
import java.net.*;

public class TimeClient {
    public static void main(String[] args) throws Exception {
        String serverIP = "127.0.0.1";
        int PORT = 5000;

        Socket socket = new Socket(serverIP, PORT);
        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        String msg = in.readUTF(); // wait for server
        if(msg.equals("SEND_TIME")) {
            long localTime = System.currentTimeMillis();
            System.out.println("Local Time : " + localTime);
            out.writeLong(localTime);

            long offset = in.readLong(); // offset from server
            long adjustedTime = localTime + offset;
            System.out.println("Offset Recvd  : " + offset);
            System.out.println("Adjusted Time : " + adjustedTime);
        }
        socket.close();
    }
}
