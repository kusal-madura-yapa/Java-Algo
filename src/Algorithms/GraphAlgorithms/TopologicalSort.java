package Algorithms.GraphAlgorithms;

/**
 * A class implementing Topological Sort in Java.
 * Topological Sort orders vertices in a directed acyclic graph (DAG) such that for every
 * directed edge (u, v), vertex u comes before v in the ordering. This implementation
 * uses a depth-first search (DFS) approach and an adjacency list for the graph.
 */
public class TopologicalSort {
    // Number of vertices and adjacency list for the graph
    private int vertices;
    private java.util.List<Integer>[] adjList;

    // Constructor to initialize the graph
    @SuppressWarnings("unchecked")
    public TopologicalSort(int vertices) {
        this.vertices = vertices;
        adjList = new java.util.ArrayList[vertices];
        for (int i = 0; i < vertices; i++) {
            adjList[i] = new java.util.ArrayList<>();
        }
    }

    // Method to add a directed edge to the graph
    public void addEdge(int src, int dest) {
        if (src < 0 || src >= vertices || dest < 0 || dest >= vertices) {
            throw new IllegalArgumentException("Vertex index out of bounds");
        }
        adjList[src].add(dest);
    }

    // Method to perform Topological Sort
    public void topologicalSort() {
        // Stack to store the topological order
        java.util.Stack<Integer> stack = new java.util.Stack<>();
        // Array to mark visited vertices
        boolean[] visited = new boolean[vertices];

        // Perform DFS for each unvisited vertex
        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                topologicalSortUtil(i, visited, stack);
            }
        }

        // Print the topological order
        System.out.print("Topological Sort (at 07:32 PM +0530, June 05, 2025): ");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + " ");
        }
        System.out.println();
    }

    // Helper method for DFS-based topological sort
    private void topologicalSortUtil(int vertex, boolean[] visited, java.util.Stack<Integer> stack) {
        // Mark current vertex as visited
        visited[vertex] = true;

        // Visit all adjacent vertices
        for (int next : adjList[vertex]) {
            if (!visited[next]) {
                topologicalSortUtil(next, visited, stack);
            }
        }

        // Push current vertex to stack after all its adjacent vertices are processed
        stack.push(vertex);
    }

    // Main method to demonstrate Topological Sort
    public static void main(String[] args) {
        // Test Case 1: Simple DAG
        TopologicalSort graph1 = new TopologicalSort(6);
        graph1.addEdge(5, 2);
        graph1.addEdge(5, 0);
        graph1.addEdge(4, 0);
        graph1.addEdge(4, 1);
        graph1.addEdge(2, 3);
        graph1.addEdge(3, 1);
        System.out.println("Test Case 1: Simple DAG");
        graph1.topologicalSort();
        System.out.println();

        // Test Case 2: Another DAG
        TopologicalSort graph2 = new TopologicalSort(4);
        graph2.addEdge(0, 1);
        graph2.addEdge(0, 2);
        graph2.addEdge(1, 3);
        graph2.addEdge(2, 3);
        System.out.println("Test Case 2: Another DAG");
        graph2.topologicalSort();
        System.out.println();

        // Error case: Invalid edge
        try {
            TopologicalSort graph3 = new TopologicalSort(3);
            graph3.addEdge(0, 3);
        } catch (Exception e) {
            System.out.println("Error Case: " + e.getMessage());
        }
    }
}
