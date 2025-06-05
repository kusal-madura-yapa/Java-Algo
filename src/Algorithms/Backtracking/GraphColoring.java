package Algorithms.Backtracking;

public class GraphColoring {
    private int vertices; // Number of vertices
    private int[][] adjMatrix; // Adjacency matrix to represent the graph
    private int[] colors; // Array to store the color of each vertex

    // Constructor to initialize the graph with a given number of vertices
    public GraphColoring(int vertices) {
        this.vertices = vertices;
        this.adjMatrix = new int[vertices][vertices];
        this.colors = new int[vertices];
        // Initialize all vertices with no color (0)
        for (int i = 0; i < vertices; i++) {
            colors[i] = 0;
        }
    }

    // Add an edge to the graph (undirected)
    public void addEdge(int v1, int v2) {
        if (v1 < 0 || v1 >= vertices || v2 < 0 || v2 >= vertices) {
            throw new IllegalArgumentException("Vertex index out of bounds");
        }
        adjMatrix[v1][v2] = 1;
        adjMatrix[v2][v1] = 1; // Undirected graph
    }

    // Check if it's safe to assign a color to a vertex
    private boolean isSafe(int vertex, int color) {
        for (int i = 0; i < vertices; i++) {
            if (adjMatrix[vertex][i] == 1 && colors[i] == color) {
                return false; // Adjacent vertex has the same color
            }
        }
        return true;
    }

    // Recursive utility method to solve graph coloring
    private boolean graphColoringUtil(int m, int vertex) {
        // Base case: if all vertices are colored, return true
        if (vertex == vertices) {
            return true;
        }
        // Try each color from 1 to m
        for (int color = 1; color <= m; color++) {
            if (isSafe(vertex, color)) {
                colors[vertex] = color; // Assign color
                if (graphColoringUtil(m, vertex + 1)) {
                    return true; // If coloring is possible, return true
                }
                colors[vertex] = 0; // Backtrack: remove color
            }
        }
        return false; // No color can be assigned
    }

    // Solve the graph coloring problem with m colors
    public boolean colorGraph(int m) {
        if (!graphColoringUtil(m, 0)) {
            System.out.println("No solution exists with " + m + " colors");
            return false;
        }
        return true;
    }

    // Print the color assignment
    public void printColors() {
        System.out.println("Vertex colors:");
        for (int i = 0; i < vertices; i++) {
            System.out.println("Vertex " + i + ": Color " + colors[i]);
        }
    }

    // Example usage
    public static void main(String[] args) {
        // Create a graph with 4 vertices
        GraphColoring graph = new GraphColoring(4);

        // Add edges (example: a simple graph)
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);

        // Try to color the graph with 3 colors
        int numColors = 3;
        System.out.println("Attempting to color the graph with " + numColors + " colors...");
        if (graph.colorGraph(numColors)) {
            graph.printColors();
        }

        // Try with 2 colors (may fail for this graph)
        System.out.println("\nAttempting to color the graph with 2 colors...");
        GraphColoring graph2 = new GraphColoring(4);
        graph2.addEdge(0, 1);
        graph2.addEdge(0, 2);
        graph2.addEdge(0, 3);
        graph2.addEdge(1, 2);
        graph2.addEdge(1, 3);
        graph2.addEdge(2, 3);
        if (graph2.colorGraph(2)) {
            graph2.printColors();
        }
    }
}