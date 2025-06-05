package Algorithms.GraphAlgorithms;

public class FloydWarshallAlgorithm {
    // Constant to represent infinity (no direct path)
    private static final int INF = Integer.MAX_VALUE / 2;

    // Compute all-pairs shortest paths using Floyd-Warshall algorithm
    public static int[][] floydWarshall(int[][] graph) {
        int V = graph.length;
        // Initialize the distance matrix
        int[][] dist = new int[V][V];

        // Copy input graph to distance matrix
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                dist[i][j] = graph[i][j];
            }
        }

        // Update distances through each intermediate vertex
        for (int k = 0; k < V; k++) {
            for (int i = 0; i < V; i++) {
                for (int j = 0; j < V; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF && dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        return dist;
    }

    // Print the distance matrix
    private static void printSolution(int[][] dist) {
        int V = dist.length;
        System.out.println("Shortest distances between every pair of vertices:");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (dist[i][j] == INF) {
                    System.out.print("INF ");
                } else {
                    System.out.print(dist[i][j] + "   ");
                }
            }
            System.out.println();
        }
    }

    // Example usage
    public static void main(String[] args) {
        // Example graph represented as an adjacency matrix
        // INF indicates no direct path between vertices
        int[][] graph = {
                {0,   5,   INF, 10},
                {INF, 0,   3,   INF},
                {INF, INF, 0,   1},
                {INF, INF, INF, 0}
        };

        System.out.println("Input Graph (Adjacency Matrix):");
        printSolution(graph);

        // Compute shortest paths
        int[][] result = floydWarshall(graph);

        // Print the result
        System.out.println("\nResulting Shortest Paths:");
        printSolution(result);

        // Test with another graph
        int[][] graph2 = {
                {0,   3,   INF, 7},
                {8,   0,   2,   INF},
                {5,   INF, 0,   1},
                {2,   INF, INF, 0}
        };

        System.out.println("\nAnother Graph (Adjacency Matrix):");
        printSolution(graph2);

        int[][] result2 = floydWarshall(graph2);
        System.out.println("\nResulting Shortest Paths for Second Graph:");
        printSolution(result2);
    }
}