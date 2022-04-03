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
    static int[] available, work;

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
                return;
            }
            for (int j = 0; j < need[i].length; j++) {
                work[j] += need[threadNo][j];
            }
        }
        for (int i : safeSequence) {
            System.out.print(i + " ");
        }
        System.out.println("");
    }

    public static void calcNeed() {
        need = new int[allocation.length][allocation[0].length];
        for (int i = 0; i < allocation.length; i++) {
            for (int j = 0; j < allocation[i].length; j++) {
                need[i][j] = max[i][j] - allocation[i][j];
            }
        }
    }

    public static void display(int[][] arr) {
        for (int[] i : arr) {
            for (int j : i) {
                System.out.print(j + " ");
            }
            System.out.println("");
        }
    }

    public static void main(String args[]) throws FileNotFoundException {
        System.setIn(new FileInputStream("./input.txt"));
        fs = new Scanner(System.in);
        int threads, products;

        out.println("Enter no. of threads: ");
        threads = fs.nextInt();
        out.println("Enter no. of products: ");
        products = fs.nextInt();

        allocation = new int[threads][products];
        for (int i = 0; i < threads; i++) {
            System.out.printf("Enter the allocation of %d\n", i + 1);
            for (int j = 0; j < products; j++) {
                allocation[i][j] = fs.nextInt();
            }
        }
        max = new int[threads][products];
        for (int i = 0; i < threads; i++) {
            out.printf("Enter the max need of thread %d\n", i + 1);
            for (int j = 0; j < products; j++) {
                max[i][j] = fs.nextInt();
            }
        }
        out.printf("Enter the available of all the products\n");
        available = new int[products];
        for (int i = 0; i < products; i++) {
            available[i] = fs.nextInt();
        }
        calcNeed();
        calcSafeSequence();
        fs.close();
    }
}
