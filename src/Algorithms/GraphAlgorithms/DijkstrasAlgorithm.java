package Algorithms.GraphAlgorithms;

/**
 * A class implementing Dijkstra's Algorithm in Java.
 * Dijkstra's Algorithm finds the shortest paths from a source vertex to all other
 * vertices in a weighted directed graph with non-negative weights.
 * This implementation uses an adjacency list and a priority queue for efficiency.
 */
public class DijkstrasAlgorithm {
    // Class to represent an edge in the graph
    private static class Edge {
        int dest, weight;

        Edge(int dest, int weight) {
            this.dest = dest;
            this.weight = weight;
        }
    }

    // Class to represent a vertex and its distance for the priority queue
    private static class Vertex implements Comparable<Vertex> {
        int id, distance;

        Vertex(int id, int distance) {
            this.id = id;
            this.distance = distance;
        }

        @Override
        public int compareTo(Vertex other) {
            return Integer.compare(this.distance, other.distance);
        }
    }

    // Number of vertices and adjacency list for the graph
    private int vertices;
    private java.util.List<Edge>[] adjList;

    // Constructor to initialize the graph
    @SuppressWarnings("unchecked")
    public DijkstrasAlgorithm(int vertices) {
        this.vertices = vertices;
        adjList = new java.util.ArrayList[vertices];
        for (int i = 0; i < vertices; i++) {
            adjList[i] = new java.util.ArrayList<>();
        }
    }

    // Method to add an edge to the graph
    public void addEdge(int src, int dest, int weight) {
        if (src < 0 || src >= vertices || dest < 0 || dest >= vertices || weight < 0) {
            throw new IllegalArgumentException("Invalid vertex or negative weight");
        }
        adjList[src].add(new Edge(dest, weight));
    }

    // Method to run Dijkstra's algorithm from a source vertex
    public void dijkstra(int source) {
        if (source < 0 || source >= vertices) {
            throw new IllegalArgumentException("Source vertex out of bounds");
        }

        // Initialize distances
        int[] distance = new int[vertices];
        for (int i = 0; i < vertices; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        distance[source] = 0;

        // Priority queue to store vertices and their distances
        java.util.PriorityQueue<Vertex> pq = new java.util.PriorityQueue<>();
        pq.add(new Vertex(source, 0));

        // Process vertices
        while (!pq.isEmpty()) {
            int u = pq.poll().id;

            // Explore all adjacent vertices of u
            for (Edge edge : adjList[u]) {
                int v = edge.dest;
                int weight = edge.weight;

                // Relax the edge if a shorter path is found
                if (distance[u] != Integer.MAX_VALUE && distance[u] + weight < distance[v]) {
                    distance[v] = distance[u] + weight;
                    pq.add(new Vertex(v, distance[v]));
                }
            }
        }

        // Print the shortest distances
        printDistances(distance, source);
    }

    // Method to print the shortest distances from the source
    private void printDistances(int[] distance, int source) {
        System.out.println("Shortest distances from vertex " + source + " at 07:30 PM +0530, June 05, 2025:");
        for (int i = 0; i < vertices; i++) {
            if (distance[i] == Integer.MAX_VALUE) {
                System.out.println("Vertex " + i + ": Infinity");
            } else {
                System.out.println("Vertex " + i + ": " + distance[i]);
            }
        }
    }

    // Main method to demonstrate Dijkstra's algorithm
    public static void main(String[] args) {
        // Test Case 1: Simple graph
        DijkstrasAlgorithm graph1 = new DijkstrasAlgorithm(4);
        graph1.addEdge(0, 1, 10);
        graph1.addEdge(0, 2, 5);
        graph1.addEdge(1, 2, 2);
        graph1.addEdge(1, 3, 1);
        graph1.addEdge(2, 1, 3);
        graph1.addEdge(2, 3, 9);
        graph1.addEdge(3, 0, 7);
        System.out.println("Test Case 1: Simple Graph");
        graph1.dijkstra(0);
        System.out.println();

        // Test Case 2: Graph with longer paths
        DijkstrasAlgorithm graph2 = new DijkstrasAlgorithm(5);
        graph2.addEdge(0, 1, 4);
        graph2.addEdge(0, 2, 8);
        graph2.addEdge(1, 2, 2);
        graph2.addEdge(1, 3, 5);
        graph2.addEdge(2, 3, 5);
        graph2.addEdge(2, 4, 10);
        graph2.addEdge(3, 4, 2);
        System.out.println("Test Case 2: Graph with Longer Paths");
        graph2.dijkstra(0);
        System.out.println();

        // Error case: Invalid source
        try {
            graph2.dijkstra(5);
        } catch (Exception e) {
            System.out.println("Error Case: " + e.getMessage());
        }
    }
}
