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
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String example = "Convert Java String";
        byte[] bytes = example.getBytes();

        for (byte b : bytes) {
            mem.put(b);
            Thread.sleep(1);
        }
    }
}


  public class MemoryMappedFileInJava {

    private static int count = 10485760; 

    public static void main(String[] args) throws Exception {

        RandomAccessFile memoryMappedFile = new RandomAccessFile("largeFile.txt", "rw");
        MappedByteBuffer out = memoryMappedFile.getChannel().map(FileChannel.MapMode.READ_WRITE, 0, count);

        for (int i = 0; i < count; i++) {
            out.put((byte) 'A');
        }

        System.out.println("Writing to Memory Mapped File is completed");

        for (int i = 0; i < 10 ; i++) {
            System.out.print((char) out.get(i));
        }

        System.out.println("Reading from Memory Mapped File is completed");

    }

}
