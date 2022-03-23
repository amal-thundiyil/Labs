import java.net.*;
import java.io.*;

public class Server {
    public static void main(String args[]) throws Exception {
        ServerSocket serversocket = new ServerSocket(6464);
        Socket socket = serversocket.accept();
        DataInputStream dina = new DataInputStream(socket.getInputStream());
        DataInputStream dinb = new DataInputStream(socket.getInputStream());
        DataOutputStream doutputstrm = new DataOutputStream(socket.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int x, y, z;
        x = dina.readInt();
        y = dinb.readInt();
        z = x + y;
        doutputstrm.writeInt(z);
        doutputstrm.flush();
        dina.close();
        dinb.close();
        socket.close();
        serversocket.close();
    }
}
