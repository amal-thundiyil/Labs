import java.lang.Math;
import java.lang.Thread;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ExecutorService;

class ThreadClass extends Thread {

    public int findLargest(int[] arr) {
        int maxx = Integer.MIN_VALUE;
        for (int elem : arr)
            maxx = Math.max(maxx, elem);
        return maxx;
    }

    public int[] createArray() {
        int N = (int) Math.pow(10, 8);
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = (int) (Math.random() * 100);
        }
        return arr;
    }

    public void run() {
        int[] arr = createArray();
        int maxx = findLargest(arr);
    }
}

public class Main {

    public static void main(String[] args) {
        for (int i = 1; i <= 10; i++) {
            ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
            long start = System.nanoTime();
            for (int j = 1; j <= i; j++) {
                service.submit(new ThreadClass());
            }
            service.shutdown();
            try {
                service.awaitTermination(1, TimeUnit.HOURS); 
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            long time = System.nanoTime() - start;
            System.out.printf(i + " thread(s) took %.3f ms to run%n", time / 1e6);
        }
    }
}