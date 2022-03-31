import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;
import static java.lang.Math.*;
import static java.lang.System.out;

import java.io.FileInputStream;
import java.util.*;

public class A {
	static ArrayList<Integer> getSafeSeq() {
		int work[] = Arrays.copyOf(available, m);
		boolean finish[] = new boolean[n];
		ArrayList<Integer> seq = new ArrayList<>();
		int ans = -1;
		for(int i = 0 ; i < n ; i++) {
			for(int j = 0 ; j < n ; j++) {
				boolean isless = true;
				if(finish[j])continue;
				for(int k = 0 ; k < m ; k++) {
					isless &= need[j][k] <= work[k];
				}
				if(isless) {
					ans = j;
					finish[j] = true;
					for(int k = 0 ; k < m ; k++) {
						work[k] += allocation[ans][k];
					}
					break;
				}
			}
			if(ans == -1) throw null;
			seq.add(ans);
		}
		return seq;
	}
	
	static ReentrantLock rl;

	static class ProcessExecution implements Runnable {
		int t_no;
		public ProcessExecution(int t_no) {
			this.t_no = t_no;
		}
		@Override
		public void run() {
			rl.lock();
			out.println("Acquired the lock");
			
		}
	}

	static int n, m;
	static int allocation[][];
	static int max_need[][];
	static int need[][];
	static int available[];
	
	public static void main(String args[]) throws Exception {
		System.setIn(new FileInputStream("./input.txt"));
		rl = new ReentrantLock();
		Scanner fs = new Scanner(System.in);
		out.println("Enter the number of threads");
		n = fs.nextInt();
		out.println("Enter the numner of products");
		m = fs.nextInt();
		allocation = new int[n][m];
		for (int i = 0; i < n; i++) {
			out.printf("Enter the allocation of %d\n", i + 1);
			for (int j = 0; j < m; j++) {
				allocation[i][j] = fs.nextInt();
			}
		}
		max_need = new int[n][m];
		for (int i = 0; i < n; i++) {
			out.printf("Enter the max of thread %d\n", i + 1);
			for (int j = 0; j < m; j++) {
				max_need[i][j] = fs.nextInt();
			}
		}
		out.printf("Enter the available of all the products\n");
		available = new int[m];
		for (int i = 0; i < m; i++) {
			available[i] = fs.nextInt();
		}
		need = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				need[i][j] = max_need[i][j] - allocation[i][j];
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				out.printf("%d\t", need[i][j]);
			}
			out.println();
		}
		ArrayList<Integer> safe_sequence = getSafeSeq();
		Thread th[] = new Thread[n];
		ProcessExecution pr[] = new ProcessExecution[n];
		for(int i = 0; i < n ; i++) {
			pr[i] = new ProcessExecution(safe_sequence.get(i));
		}
	}
}
