import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class Proc1 {

    public static void main(String args[]) throws IOException, InterruptedException {

        RandomAccessFile rd = new RandomAccessFile("./mapped.txt", "rw");
        FileChannel fc = rd.getChannel();
        MappedByteBuffer mem = fc.map(FileChannel.MapMode.READ_WRITE, 0, 1000);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 1; i < 1000; i++) {
            mem.put((byte) i);
            System.out.println("Process 1 : " + (byte) i);
            Thread.sleep(1);
        }
    }
}