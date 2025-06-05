package Algorithms.GraphAlgorithms;

public class PrimsAlgorithm {
    // Find the Minimum Spanning Tree using Prim's algorithm
    public static void primMST(int[][] graph) {
        int V = graph.length;
        // Array to store parent nodes in MST
        int[] parent = new int[V];
        // Array to store minimum edge weights to include a vertex in MST
        int[] key = new int[V];
        // Array to track vertices included in MST
        boolean[] mstSet = new boolean[V];

        // Initialize all keys as infinite and mstSet as false
        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        // Start with the first vertex
        key[0] = 0; // Make key 0 so that this vertex is picked first
        parent[0] = -1; // First node has no parent

        // Construct MST with V-1 edges
        for (int count = 0; count < V - 1; count++) {
            // Find the vertex with minimum key value from the set of vertices not yet included
            int u = minKey(key, mstSet, V);
            mstSet[u] = true; // Include vertex u in MST

            // Update key values and parent for adjacent vertices of u
            for (int v = 0; v < V; v++) {
                // Update if graph[u][v] is not zero (edge exists), v is not in MST, and weight is smaller than key[v]
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        // Print the MST
        printMST(parent, graph, V);
    }

    // Find the vertex with minimum key value from vertices not yet included in MST
    private static int minKey(int[] key, boolean[] mstSet, int V) {
        int min = Integer.MAX_VALUE, minIndex = -1;

        for (int v = 0; v < V; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }
        return minIndex;
    }

    // Print the constructed MST
    private static void printMST(int[] parent, int[][] graph, int V) {
        System.out.println("Edge \tWeight");
        int totalWeight = 0;
        for (int i = 1; i < V; i++) {
            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
            totalWeight += graph[i][parent[i]];
        }
        System.out.println("Total MST Weight: " + totalWeight);
    }

    // Example usage
    public static void main(String[] args) {
        // Example graph represented as an adjacency matrix
        // 0 indicates no edge, non-zero values indicate edge weights
        int[][] graph = {
                {0, 2, 0, 6, 0},
                {2, 0, 3, 8, 5},
                {0, 3, 0, 0, 7},
                {6, 8, 0, 0, 9},
                {0, 5, 7, 9, 0}
        };

        System.out.println("Graph Adjacency Matrix:");
        for (int[] row : graph) {
            System.out.println(java.util.Arrays.toString(row));
        }

        System.out.println("\nMinimum Spanning Tree (Prim's Algorithm):");
        primMST(graph);

        // Another test case
        System.out.println("\nAnother Graph Example:");
        int[][] graph2 = {
                {0, 4, 0, 0, 8},
                {4, 0, 8, 0, 0},
                {0, 8, 0, 7, 0},
                {0, 0, 7, 0, 9},
                {8, 0, 0, 9, 0}
        };
        System.out.println("Graph Adjacency Matrix:");
        for (int[] row : graph2) {
            System.out.println(java.util.Arrays.toString(row));
        }
        System.out.println("\nMinimum Spanning Tree (Prim's Algorithm):");
        primMST(graph2);
    }
}

