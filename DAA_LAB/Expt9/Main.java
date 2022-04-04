import java.util.*;
import java.io.FileInputStream;

public class Main {
    static Scanner fs;
    static int ub;
    static List<Integer> ans;

    static class Node {
        int u, c, index;

        Node(int u, int c, int index) {
            this.u = u;
            this.c = c;
            this.index = index;
        }
    }

    public static void bb(PriorityQueue<Node> nodes, int w, int M, int N, int profit[], int weight[]) {
        while (!nodes.isEmpty()) {
            Node node = nodes.poll();
            if (node.index == N) {
                return;
            } else if (w > M || node.c > ub) {
                w -= weight[node.index];
                ans.set(ans.size() - 1, 0);
                continue;
            }
            
            ub = Math.min(node.u, ub);
            int u = node.u - profit[node.index];
            int c = node.c - profit[node.index];
            ans.add(1);
            if (w + weight[node.index] > M) {
                c = node.c - ((profit[node.index] / weight[node.index]) * (M - w));
            }
            w += weight[node.index];
            System.out.println("u = " + node.u + ", c = " + node.c + ", ub = " + ub);
            nodes.add(new Node(u, c, node.index + 1));
            nodes.add(new Node(node.u, node.c, node.index + 1));
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input.txt"));
        fs = new Scanner(System.in);
        int N = fs.nextInt();
        int M = fs.nextInt();
        ans = new ArrayList<>();
        ub = Integer.MAX_VALUE;
        Node node = new Node(0, 0, 0);
        int profit[] = new int[N];
        int weight[] = new int[N];
        PriorityQueue<Node> q = new PriorityQueue<>((Node a, Node b) -> (a.c - b.c));
        q.add(node);

        for (int i = 0; i < N; i++) {
            profit[i] = fs.nextInt();
        }
        for (int i = 0; i < N; i++) {
            weight[i] = fs.nextInt();
        }
        bb(q, 0, M, N, profit, weight);
        System.out.println(ans.toString());
    }
}