package Algorithms.GraphAlgorithms;

/**
 * A class implementing Breadth-First Search (BFS) in Java.
 * BFS is a graph traversal algorithm that explores all vertices at the current depth
 * before moving to vertices at the next depth. This implementation uses an adjacency
 * list representation for the graph and includes a method to perform BFS from a source vertex.
 */
public class BreadthFirstSearch {
    // Class to represent the graph using an adjacency list
    private int vertices; // Number of vertices
    private java.util.LinkedList<Integer>[] adjList; // Adjacency list

    // Constructor to initialize the graph
    @SuppressWarnings("unchecked")
    public BreadthFirstSearch(int vertices) {
        this.vertices = vertices;
        adjList = new java.util.LinkedList[vertices];
        for (int i = 0; i < vertices; i++) {
            adjList[i] = new java.util.LinkedList<>();
        }
    }

    // Method to add an edge to the graph
    public void addEdge(int v, int w) {
        if (v < 0 || v >= vertices || w < 0 || w >= vertices) {
            throw new IllegalArgumentException("Vertex index out of bounds");
        }
        adjList[v].add(w); // Add w to v's adjacency list
    }

    // Method to perform Breadth-First Search from a source vertex
    public void bfs(int source) {
        if (source < 0 || source >= vertices) {
            throw new IllegalArgumentException("Source vertex out of bounds");
        }

        // Mark all vertices as not visited
        boolean[] visited = new boolean[vertices];
        // Create a queue for BFS
        java.util.LinkedList<Integer> queue = new java.util.LinkedList<>();

        // Mark the source as visited and enqueue it
        visited[source] = true;
        queue.add(source);

        System.out.print("BFS starting from vertex " + source + ": ");
        while (!queue.isEmpty()) {
            // Dequeue a vertex and print it
            int vertex = queue.poll();
            System.out.print(vertex + " ");

            // Get all adjacent vertices of the dequeued vertex
            for (int next : adjList[vertex]) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.add(next);
                }
            }
        }
        System.out.println();
    }

    // Main method to demonstrate BFS
    public static void main(String[] args) {
        // Test Case 1: Simple graph
        BreadthFirstSearch graph1 = new BreadthFirstSearch(4);
        graph1.addEdge(0, 1);
        graph1.addEdge(0, 2);
        graph1.addEdge(1, 2);
        graph1.addEdge(2, 0);
        graph1.addEdge(2, 3);
        graph1.addEdge(3, 3);
        System.out.println("Test Case 1: Simple Graph");
        graph1.bfs(2);
        System.out.println();

        // Test Case 2: Disconnected graph
        BreadthFirstSearch graph2 = new BreadthFirstSearch(5);
        graph2.addEdge(0, 1);
        graph2.addEdge(1, 2);
        graph2.addEdge(3, 4);
        System.out.println("Test Case 2: Disconnected Graph");
        graph2.bfs(0);
        System.out.println();

        // Error case: Invalid source vertex
        try {
            graph2.bfs(5);
        } catch (Exception e) {
            System.out.println("Error Case: " + e.getMessage());
        }
    }
}