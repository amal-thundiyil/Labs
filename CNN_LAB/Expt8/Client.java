import java.net.*;
import java.io.*;
import java.util.*;

public class Client {
    private Socket socket = null;
    private InputStreamReader in = null;
    private OutputStreamWriter out = null;
    private BufferedReader buffread = null;
    private BufferedWriter buffwrite = null;

    Client() throws Exception {
        socket = new Socket("127.0.0.1", 8080);
        System.out.println("Connected to server");
        in = new InputStreamReader(socket.getInputStream());
        out = new OutputStreamWriter(socket.getOutputStream());
        buffread = new BufferedReader(in);
        buffwrite = new BufferedWriter(out);
        Scanner fs = new Scanner(System.in);
        String line = "";
        while (true) {
            line = fs.nextLine();
            buffwrite.write(line);
            buffwrite.newLine();
            buffwrite.flush();
            System.out.println("Server:" + buffread.readLine());
            if (line.equals("Bye")) {
                break;
            }
        }
        fs.close();
        socket.close();
        in.close();
        out.close();
    }

    public static void main(String args[]) throws Exception {
        Client client = new Client();
    }
}