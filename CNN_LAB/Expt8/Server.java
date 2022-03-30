import java.net.*;
import java.io.*;

public class Server {
    Socket socket1 = null;
    Socket socket2 = null;
    InputStreamReader in1 = null;
    InputStreamReader in2 = null;
    OutputStreamWriter out1 = null;
    OutputStreamWriter out2 = null;
    BufferedReader buffread1 = null;
    BufferedWriter buffwrite2 = null;
    BufferedReader buffread2 = null;
    BufferedWriter buffwrite1 = null;
    ServerSocket server = null;

    Server(int port) throws Exception {
        server = new ServerSocket(port);
        System.out.println("Waiting for clients");
        socket1 = server.accept();
        System.out.println("Client1 Connected");
        socket2 = server.accept();
        System.out.println("Client2 Connected");
        in1 = new InputStreamReader(socket1.getInputStream());
        in2 = new InputStreamReader(socket2.getInputStream());
        out1 = new OutputStreamWriter(socket1.getOutputStream());
        out2 = new OutputStreamWriter(socket2.getOutputStream());
        buffread1 = new BufferedReader(in1);
        buffread2 = new BufferedReader(in2);
        buffwrite1 = new BufferedWriter(out1);
        buffwrite2 = new BufferedWriter(out2);
        while (true) {
            String line1 = buffread1.readLine();
            System.out.println("Client1" + line1);
            buffwrite2.write(line1);
            buffwrite2.newLine();
            buffwrite2.flush();
            if (line1.equals("Bye")) {
                break;
            }
            String line2 = buffread2.readLine();
            System.out.println("Client2" + line2);
            buffwrite1.write(line2);
            buffwrite1.newLine();
            buffwrite1.flush();
            if (line2.equals("Bye")) {
                break;
            }
        }
        server.close();
        socket1.close();
        socket2.close();
        in1.close();
        in2.close();
        out1.close();
        out2.close();
    }

    public static void main(String args[]) throws Exception {
        Server server = new Server(8080);
    }
}