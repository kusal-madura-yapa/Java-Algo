package Algorithms.GraphAlgorithms;

/**
 * A class implementing the Bellman-Ford Algorithm in Java.
 * The Bellman-Ford Algorithm finds the shortest paths from a source vertex to all other
 * vertices in a weighted directed graph, handling negative weight edges. It also detects
 * negative weight cycles. This implementation includes a graph representation and examples.
 */
public class BellmanFordAlgorithm {
    // Class to represent an edge in the graph
    private static class Edge {
        int source, dest, weight;

        Edge(int source, int dest, int weight) {
            this.source = source;
            this.dest = dest;
            this.weight = weight;
        }
    }

    // Method to run the Bellman-Ford algorithm
    public void bellmanFord(int vertices, Edge[] edges, int source) {
        if (edges == null || vertices <= 0) {
            throw new IllegalArgumentException("Invalid vertices or edges array");
        }

        // Initialize distances from source to all vertices as infinity
        int[] distance = new int[vertices];
        for (int i = 0; i < vertices; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        distance[source] = 0;

        // Relax all edges |V| - 1 times
        for (int i = 1; i < vertices; i++) {
            for (Edge edge : edges) {
                int u = edge.source;
                int v = edge.dest;
                int weight = edge.weight;
                if (distance[u] != Integer.MAX_VALUE && distance[u] + weight < distance[v]) {
                    distance[v] = distance[u] + weight;
                }
            }
        }

        // Check for negative weight cycles
        for (Edge edge : edges) {
            int u = edge.source;
            int v = edge.dest;
            int weight = edge.weight;
            if (distance[u] != Integer.MAX_VALUE && distance[u] + weight < distance[v]) {
                System.out.println("Graph contains a negative weight cycle!");
                return;
            }
        }

        // Print the shortest distances
        printDistances(distance, source);
    }

    // Method to print the shortest distances from the source
    private void printDistances(int[] distance, int source) {
        System.out.println("Shortest distances from vertex " + source + ":");
        for (int i = 0; i < distance.length; i++) {
            if (distance[i] == Integer.MAX_VALUE) {
                System.out.println("Vertex " + i + ": Infinity");
            } else {
                System.out.println("Vertex " + i + ": " + distance[i]);
            }
        }
    }

    // Main method to demonstrate the Bellman-Ford algorithm
    public static void main(String[] args) {
        BellmanFordAlgorithm bf = new BellmanFordAlgorithm();

        // Test Case 1: Graph without negative cycle
        int vertices1 = 5;
        Edge[] edges1 = {
                new Edge(0, 1, -1),
                new Edge(0, 2, 4),
                new Edge(1, 2, 3),
                new Edge(1, 3, 2),
                new Edge(1, 4, 2),
                new Edge(3, 2, 5),
                new Edge(3, 1, 1),
                new Edge(4, 3, -3)
        };
        System.out.println("Test Case 1: Graph without negative cycle");
        bf.bellmanFord(vertices1, edges1, 0);
        System.out.println();

        // Test Case 2: Graph with negative cycle
        int vertices2 = 4;
        Edge[] edges2 = {
                new Edge(0, 1, 1),
                new Edge(1, 2, -1),
                new Edge(2, 3, -1),
                new Edge(3, 1, -1)
        };
        System.out.println("Test Case 2: Graph with negative cycle");
        bf.bellmanFord(vertices2, edges2, 0);
        System.out.println();

        // Error case: Invalid input
        try {
            bf.bellmanFord(0, new Edge[]{}, 0);
        } catch (Exception e) {
            System.out.println("Error Case: " + e.getMessage());
        }
    }
}
