package Algorithms.GraphAlgorithms;

public class DepthFirstSearch {
    // Class to represent a graph using adjacency list
    private static class Graph {
        private int vertices; // Number of vertices
        private java.util.LinkedList<Integer>[] adj; // Adjacency list

        // Constructor
        @SuppressWarnings("unchecked")
        Graph(int vertices) {
            this.vertices = vertices;
            adj = new java.util.LinkedList[vertices];
            for (int i = 0; i < vertices; i++) {
                adj[i] = new java.util.LinkedList<>();
            }
        }

        // Add an edge to the graph
        void addEdge(int v, int w) {
            adj[v].add(w); // Directed graph
        }

        // Perform DFS starting from a given vertex
        void dfs(int startVertex) {
            // Mark all vertices as not visited
            boolean[] visited = new boolean[vertices];

            // Call recursive DFS helper
            System.out.print("DFS starting from vertex " + startVertex + ": ");
            dfsUtil(startVertex, visited);
            System.out.println();
        }

        // Recursive helper for DFS
        private void dfsUtil(int vertex, boolean[] visited) {
            // Mark current vertex as visited
            visited[vertex] = true;
            System.out.print(vertex + " ");

            // Explore all adjacent vertices
            for (int neighbor : adj[vertex]) {
                if (!visited[neighbor]) {
                    dfsUtil(neighbor, visited);
                }
            }
        }

        // Perform DFS for the entire graph (handles disconnected components)
        void dfsAll() {
            // Mark all vertices as not visited
            boolean[] visited = new boolean[vertices];
            System.out.println("DFS traversal of entire graph:");

            // Visit each unvisited vertex
            for (int i = 0; i < vertices; i++) {
                if (!visited[i]) {
                    System.out.print("Component: ");
                    dfsUtil(i, visited);
                    System.out.println();
                }
            }
        }
    }

    // Example usage
    public static void main(String[] args) {
        // Create a graph with 6 vertices
        Graph graph = new Graph(6);

        // Add edges
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);
        graph.addEdge(3, 5);

        // Perform DFS starting from vertex 0
        graph.dfs(0);

        // Perform DFS for the entire graph
        graph.dfsAll();

        // Test with a disconnected graph
        System.out.println("\nTesting disconnected graph:");
        Graph disconnectedGraph = new Graph(6);
        disconnectedGraph.addEdge(0, 1);
        disconnectedGraph.addEdge(1, 2);
        disconnectedGraph.addEdge(3, 4);
        disconnectedGraph.addEdge(4, 5);
        disconnectedGraph.dfsAll();
    }
}
