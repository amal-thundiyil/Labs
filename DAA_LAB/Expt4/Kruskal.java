import java.util.*;

public class Kruskal {

    private static class Edge {

        private int from;
        private int to;
        private int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    private static void addEdge(HashSet<Edge>[] graph, int from, int to, int weight) {
        graph[from].add(new Edge(from, to, weight));
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int nodes = in.nextInt();
        int edges = in.nextInt();
        HashSet<Edge>[] graph = new HashSet[nodes];

        for (int i = 0; i < nodes; i++) {
            graph[i] = new HashSet<>();
        }
        for (int i = 0; i < edges; i++) {
            addEdge(graph, in.nextInt(), in.nextInt(), in.nextInt());
        }

        System.out.println("Initial Graph: ");
        for (int i = 0; i < graph.length; i++) {
            for (Edge edge : graph[i]) {
                System.out.println(i + "-(" + edge.weight + ")-" + edge.to);
            }
        }

        Kruskal k = new Kruskal();
        HashSet<Edge>[] solGraph = k.kruskal(graph);

        System.out.println("\nMinimal Graph: ");
        int minCost = 0;
        for (int i = 0; i < solGraph.length; i++) {
            for (Edge edge : solGraph[i]) {
                System.out.println(i + "-(" + edge.weight + ")-" + edge.to);
                minCost += edge.weight;
            }
        }
        System.out.println("\nMinimum Cost: " + minCost);
    }

    public HashSet<Edge>[] kruskal(HashSet<Edge>[] graph) {
        int nodes = graph.length;
        int[] captain = new int[nodes];
        HashSet<Integer>[] connectedGroups = new HashSet[nodes];
        HashSet<Edge>[] minGraph = new HashSet[nodes];
        PriorityQueue<Edge> edges = new PriorityQueue<>((Comparator.comparingInt(edge -> edge.weight)));
        for (int i = 0; i < nodes; i++) {
            minGraph[i] = new HashSet<>();
            connectedGroups[i] = new HashSet<>();
            connectedGroups[i].add(i);
            captain[i] = i;
            edges.addAll(graph[i]);
        }
        int connectedElements = 0;
        while (connectedElements != nodes && !edges.isEmpty()) {
            Edge edge = edges.poll();
            if (!connectedGroups[captain[edge.from]].contains(edge.to)
                    && !connectedGroups[captain[edge.to]].contains(edge.from)) {
                connectedGroups[captain[edge.from]].addAll(connectedGroups[captain[edge.to]]);
                connectedGroups[captain[edge.from]].forEach(i -> captain[i] = captain[edge.from]);
                addEdge(minGraph, edge.from, edge.to, edge.weight);
                connectedElements = connectedGroups[captain[edge.from]].size();
            }
        }
        return minGraph;
    }
}