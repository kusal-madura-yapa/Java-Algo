package DataStructures.Graphs;

import java.util.*;
// Class to represent a graph using an adjacency list
public class GraphAdjacencyList {
    private int vertices; // Number of vertices in the graph
    private LinkedList<Integer>[] adjacencyList; // Array of linked lists to store adjacency lists

    // Constructor to initialize the graph with a given number of vertices
    public GraphAdjacencyList(int vertices) {
        this.vertices = vertices;
        // Initialize the adjacency list for each vertex
        adjacencyList = new LinkedList[vertices];
        for (int i = 0; i < vertices; i++) {
            adjacencyList[i] = new LinkedList<>();
        }
    }

    // Method to add an edge to the graph (undirected)
    public void addEdge(int source, int destination) {
        // Check if vertices are valid
        if (source < 0 || source >= vertices || destination < 0 || destination >= vertices) {
            throw new IllegalArgumentException("Vertex index out of bounds");
        }
        // Add destination to source's adjacency list
        adjacencyList[source].add(destination);
        // Since the graph is undirected, add source to destination's adjacency list
        adjacencyList[destination].add(source);
    }

    // Method to display the adjacency list representation of the graph
    public void displayGraph() {
        System.out.println("Adjacency List Representation of the Graph:");
        for (int i = 0; i < vertices; i++) {
            System.out.print("Vertex " + i + ": ");
            for (Integer neighbor : adjacencyList[i]) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }

    // Main method with an example to demonstrate the graph
    public static void main(String[] args) {
        // Example: Create a graph with 4 vertices
        GraphAdjacencyList graph = new GraphAdjacencyList(4);

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