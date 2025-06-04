package DataStructures.Graphs;

import java.util.*;

// Class to represent a graph using an adjacency matrix
public class GraphAdjacencyMatrix {
    private int vertices; // Number of vertices in the graph
    private int[][] adjacencyMatrix; // 2D array to store the adjacency matrix

    // Constructor to initialize the graph with a given number of vertices
    public GraphAdjacencyMatrix(int vertices) {
        this.vertices = vertices;
        // Initialize the adjacency matrix with zeros
        adjacencyMatrix = new int[vertices][vertices];
    }

    // Method to add an edge to the graph (undirected)
    public void addEdge(int source, int destination) {
        // Check if vertices are valid
        if (source < 0 || source >= vertices || destination < 0 || destination >= vertices) {
            throw new IllegalArgumentException("Vertex index out of bounds");
        }
        // Add edge by setting 1 in the matrix for undirected graph
        adjacencyMatrix[source][destination] = 1;
        // Since the graph is undirected, set 1 for the reverse direction
        adjacencyMatrix[destination][source] = 1;
    }

    // Method to display the adjacency matrix representation of the graph
    public void displayGraph() {
        System.out.println("Adjacency Matrix Representation of the Graph:");
        // Print column headers
        System.out.print("  ");
        for (int i = 0; i < vertices; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        // Print matrix with row labels
        for (int i = 0; i < vertices; i++) {
            System.out.print(i + " ");
            for (int j = 0; j < vertices; j++) {
                System.out.print(adjacencyMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Main method with an example to demonstrate the graph
    public static void main(String[] args) {
        // Example: Create a graph with 4 vertices
        GraphAdjacencyMatrix graph = new GraphAdjacencyMatrix(4);

        // Add edges to create the following graph:
        // 0 -- 1
        // |    |
        // 2 -- 3
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 3);

        // Display the graph
        graph.displayGraph();
    }
}
