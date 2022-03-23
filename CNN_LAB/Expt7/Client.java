import java.net.*;
import java.io.*;

public class Client {
    public static void main(String args[]) throws Exception {
        Socket socket = new Socket("localhost", 6464);
        DataInputStream din = new DataInputStream(socket.getInputStream());
        DataOutputStream douta = new DataOutputStream(socket.getOutputStream());
        DataOutputStream doutb = new DataOutputStream(socket.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int a, b, c;
        a = Integer.parseInt(br.readLine());
        b = Integer.parseInt(br.readLine());
        douta.writeInt(a);
        douta.flush();
        doutb.writeInt(b);
        doutb.flush();
        c = din.readInt();
        System.out.println("Addition is: " + c);
        douta.close();
        doutb.close();
        socket.close();
    }
}
