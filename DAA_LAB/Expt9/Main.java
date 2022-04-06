import java.util.*;
import java.io.FileInputStream;

public class Main {
    static Scanner fs;
    static int ub;
    static List<Node> ans;
    static int N, M;

    static class Node {
        int u, c, level, taken;

        Node(int u, int c, int index, int taken) {
            this.u = u;
            this.c = c;
            this.level = index;
            this.taken = taken;
        }
    }

    public static void bb(PriorityQueue<Node> nodes, int w, int profit[], int weight[]) {
        while (!nodes.isEmpty()) {
            Node node = nodes.poll();
            if (node.level >= N) {
                return;
            } else if (w > M || node.c > ub) {
                ans.remove(node);
                w -= weight[node.level];
                continue;
            }
            ans.add(node);
            System.out.println("u = " + node.u + ", c = " + node.c + ", ub = " + ub);
            ub = Math.min(node.u, ub);
            int u = node.u + profit[node.level];
            int c = node.c + profit[node.level];
            if (w + weight[node.level] > M) {
                c = node.c + ((profit[node.level] / weight[node.level]) * (M - w));
            }
            w += weight[node.level];
            nodes.add(new Node(u, c, node.level + 1, 1));
            nodes.add(new Node(node.u, node.c, node.level + 1, 1));
        }
    }

    public static void solve(int[] profit, int[] weight) {
        int u = 0, c = 0, w = 0;
        ub = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            if (w + weight[i] > M) {
                c -= ((profit[i] / weight[i]) * (M - w));
                break;
            }
            w += weight[i];
            u -= profit[i];
            c -= profit[i];
        }
        ub = Math.min(u, ub);
        Node node = new Node(u, c, 0, 0);
        PriorityQueue<Node> q = new PriorityQueue<>((Node a, Node b) -> (b.c - a.c));
        q.add(node);
        bb(q, w, profit, weight);
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./input.txt"));
        fs = new Scanner(System.in);
        N = fs.nextInt();
        M = fs.nextInt();
        ans = new ArrayList<>();
        int profit[] = new int[N];
        int weight[] = new int[N];

        for (int i = 0; i < N; i++) {
            profit[i] = fs.nextInt();
        }
        for (int i = 0; i < N; i++) {
            weight[i] = fs.nextInt();
        }
        solve(profit, weight);
        for (Node node : ans) {
            System.out.print(node.taken + " ");
        }
    }
}