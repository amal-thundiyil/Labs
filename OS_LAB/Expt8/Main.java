import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;
import static java.lang.Math.*;
import static java.lang.System.out;
import java.io.FileInputStream;
import java.util.*;

public class Main {
    static Scanner fs;
    static int[][] allocation, max, need; 
    static List<Integer> safeSequence;
    static HashSet<Integer> safeSet;
    static int[] available, work;

    public static int[] findThreadWithMinNeed() {
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
                return allocation[i];
            }
        }
        return new int[1];
    }

    public static void calcSafeSequence() {
        int[] work = Arrays.copyOf(available);
        for (int i = 0; i < allocation.length; i++) {
            int[] minNeed = findThreadWithMinNeed();
            for (int j = 0; j < work.length; j++) {
                work[j] += minNeed[j];
            }
        }
    }

    public static void calcNeed() {
        for (int i = 0; i < allocation.length; i++) {
            for (int j = 0; j < allocation[i].length; j++) {
                need[i][j] = max[i][j] - allocation[i][j];
            }
        }
    }

    public static void display(int[][] arr) {
        for (int[] i: arr) {
            for (int j: i) {
                System.out.print(j + " ");
            }
        }
        System.out.println("");
    }

    public static void main(String args[]) {
        System.setIn(new FileInputStream("./input.txt"));
        fs = new Scanner(System.in);
        int products;
        int n;
        
        out.println("Enter no. of threads: ");
        n = fs.nextInt();
        out.println("Enter no. of products: ");
        products = fs.nextInt();
        
        int[][] allocation = new int[n][products];
		for (int i = 0; i < n; i++) {
			System.out.printf("Enter the allocation of %d\n", i + 1);
			for (int j = 0; j < products; j++) {
				allocation[i][j] = fs.nextInt();
			}
		}
        need = new int[n][products];
		for (int i = 0; i < n; i++) {
			out.printf("Enter the max of thread %d\n", i + 1);
			for (int j = 0; j < products; j++) {
				need[i][j] = fs.nextInt();
			}
		}
		out.printf("Enter the available of all the products\n");
		available = new int[products];
		for (int i = 0; i < products; i++) {
			available[i] = fs.nextInt();
		}

        calcNeed();
    }
}
