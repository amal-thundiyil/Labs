import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;
import static java.lang.Math.*;
import static java.lang.System.out;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    static Scanner fs;
    static int[][] allocation, max, need;
    static List<Integer> safeSequence = new ArrayList<>();
    static HashSet<Integer> safeSet = new HashSet<>();
    static boolean safe = true;
    static int[] available, work, request;
    static int requestingThread;
    static volatile ReentrantLock rl;

    static class ProcessExecution implements Runnable {
        int t_no;

        public ProcessExecution(int t_no) {
            this.t_no = t_no;
        }

        @Override
        public void run() {
            rl.lock();
            try {
                out.println("Acquired the lock: " + t_no);
                out.println("I am running");
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                }
                out.println("Current Available: ");
                for (int i = 0; i < allocation[t_no].length; i++) {
                    work[i] += allocation[t_no][i];
                }
                out.println(Arrays.toString(work));
            } finally {
                rl.unlock();
            }
        }
    }

    public static int findThreadWithMinNeed() {
        for (int i = 0; i < need.length; i++) {
            if (safeSet.contains(i)) {
                continue;
            }
            boolean min = true;
            for (int j = 0; j < need[i].length; j++) {
                if (need[i][j] > work[j]) {
                    min = false;
                    break;
                }
            }
            if (min == true) {
                safeSet.add(i);
                safeSequence.add(i);
                return i;
            }
        }
        return -1;
    }

    public static void calcSafeSequence() {
        work = Arrays.copyOf(available, available.length);
        for (int i = 0; i < need.length; i++) {
            int threadNo = findThreadWithMinNeed();
            if (threadNo == -1) {
                System.out.println("No safe sequence found!!");
                safe = false;
                return;
            }
            for (int j = 0; j < need[i].length; j++) {
                work[j] += need[threadNo][j];
            }
        }
    }

    public static void calcNeed() {
        need = new int[allocation.length][allocation[0].length];
        for (int i = 0; i < allocation.length; i++) {
            for (int j = 0; j < allocation[i].length; j++) {
                need[i][j] = max[i][j] - allocation[i][j];
            }
        }
    }

    public static void warnNotSafe() {
        if (!safe) {
            System.out.println("Request couldn't be granted.");
        }
    }

    public static void grantRequest() {
        warnNotSafe();
        for (int i = 0; i < available.length; i++) {
            if (request[i] > available[i]) {
                warnNotSafe();
                return;
            }
        }
        for (int i = 0; i < allocation[0].length; i++) {
            if (request[i] > allocation[requestingThread][i]) {
                warnNotSafe();
                break;
            }
        }
        System.out.println("Request can't be granted immediately. Please wait.");
    }

    public static void display() {
        System.out.println("Available: " + Arrays.toString(available));

        System.out.println("Allocation\tMax\t\tNeed");
        for (int i = 0; i < allocation.length; i++) {
            for (int j = 0; j < allocation[i].length; j++) {
                System.out.print(allocation[i][j] + " ");
            }
            System.out.print("\t\t");
            for (int j = 0; j < max[i].length; j++) {
                System.out.print(max[i][j] + " ");
            }
            System.out.print("\t\t");
            for (int j = 0; j < need[i].length; j++) {
                System.out.print(need[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("Safe Sequence: " + safeSequence.toString());
        System.out.println("Request: " + Arrays.toString(request));
    }

    public static void main(String args[]) throws FileNotFoundException {
        System.setIn(new FileInputStream("./input.txt"));
        fs = new Scanner(System.in);
        int threads, products;

        threads = fs.nextInt();
        products = fs.nextInt();

        allocation = new int[threads][products];
        for (int i = 0; i < threads; i++) {
            for (int j = 0; j < products; j++) {
                allocation[i][j] = fs.nextInt();
            }
        }
        max = new int[threads][products];
        for (int i = 0; i < threads; i++) {
            for (int j = 0; j < products; j++) {
                max[i][j] = fs.nextInt();
            }
        }
        available = new int[products];
        for (int i = 0; i < products; i++) {
            available[i] = fs.nextInt();
        }
        calcNeed();
        calcSafeSequence();
        display();

        Thread th[] = new Thread[threads];
        ProcessExecution pr[] = new ProcessExecution[threads];
        for (int i = 0; i < safeSequence.size(); i++) {
            int num = safeSequence.get(i);
            pr[num] = new ProcessExecution(num);
        }
        int ch = 1;
        while (ch != 0) {
            request = new int[products];
            for (int i = 0; i < products; i++) {
                request[i] = fs.nextInt();
            }
            requestingThread = fs.nextInt();
            System.out.println("Press 0 to exit: ");
            ch = fs.nextInt();
        }

        grantRequest();
        fs.close();
    }
}
