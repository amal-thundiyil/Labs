import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class Proc2 {

    public static void main(String args[]) throws IOException {

        RandomAccessFile rd = new RandomAccessFile("./mapped.txt", "r");
        FileChannel fc = rd.getChannel();
        MappedByteBuffer mem = fc.map(FileChannel.MapMode.READ_ONLY, 0, 1000);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int value = mem.get();
        while (value != 0) {
            System.out.println("Process 2 : " + value);
            value = mem.get();
        }
        rd.close();
    }
}
