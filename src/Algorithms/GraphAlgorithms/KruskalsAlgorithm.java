package Algorithms.GraphAlgorithms;

/**
 * A class implementing Kruskal's Algorithm in Java.
 * Kruskal's Algorithm finds the Minimum Spanning Tree (MST) of a connected, undirected
 * graph with weighted edges. This implementation uses a disjoint-set data structure
 * for efficient union-find operations and sorts edges by weight.
 */
public class KruskalsAlgorithm {
    // Class to represent an edge in the graph
    private static class Edge implements Comparable<Edge> {
        int src, dest, weight;

        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }

    // Class to represent a disjoint-set (union-find) data structure
    private static class DisjointSet {
        int[] parent, rank;

        DisjointSet(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i; // Each node is initially its own parent
            }
        }

        // Find the root of a node with path compression
        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // Path compression
            }
            return parent[x];
        }

        // Union by rank to merge two sets
        void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                if (rank[rootX] < rank[rootY]) {
                    parent[rootX] = rootY;
                } else if (rank[rootX] > rank[rootY]) {
                    parent[rootY] = rootX;
                } else {
                    parent[rootY] = rootX;
                    rank[rootX]++;
                }
            }
        }
    }

    // Method to run Kruskal's algorithm to find the MST
    public void kruskalMST(int vertices, Edge[] edges) {
        if (vertices <= 0 || edges == null) {
            throw new IllegalArgumentException("Invalid vertices or edges array");
        }

        // Sort edges by weight
        java.util.Arrays.sort(edges);

        // Initialize disjoint set for union-find
        DisjointSet ds = new DisjointSet(vertices);
        java.util.List<Edge> mst = new java.util.ArrayList<>();

        // Process each edge
        int edgeCount = 0;
        for (Edge edge : edges) {
            int srcRoot = ds.find(edge.src);
            int destRoot = ds.find(edge.dest);

            // If including this edge does not form a cycle, add it to MST
            if (srcRoot != destRoot) {
                mst.add(edge);
                ds.union(edge.src, edge.dest);
                edgeCount++;
                if (edgeCount == vertices - 1) {
                    break; // MST has V-1 edges
                }
            }
        }

        // Print the MST
        printMST(mst);
    }

    // Method to print the Minimum Spanning Tree
    private void printMST(java.util.List<Edge> mst) {
        int totalWeight = 0;
        System.out.println("Minimum Spanning Tree edges (at 07:31 PM +0530, June 05, 2025):");
        for (Edge edge : mst) {
            System.out.println("Edge: " + edge.src + " - " + edge.dest + ", Weight: " + edge.weight);
            totalWeight += edge.weight;
        }
        System.out.println("Total MST weight: " + totalWeight);
    }

    // Main method to demonstrate Kruskal's algorithm
    public static void main(String[] args) {
        KruskalsAlgorithm kruskal = new KruskalsAlgorithm();

        // Test Case 1: Simple graph
        int vertices1 = 4;
        Edge[] edges1 = {
                new Edge(0, 1, 10),
                new Edge(0, 2, 6),
                new Edge(0, 3, 5),
                new Edge(1, 3, 15),
                new Edge(2, 3, 4)
        };
        System.out.println("Test Case 1: Simple Graph");
        kruskal.kruskalMST(vertices1, edges1);
        System.out.println();

        // Test Case 2: Larger graph
        int vertices2 = 5;
        Edge[] edges2 = {
                new Edge(0, 1, 7),
                new Edge(0, 2, 8),
                new Edge(1, 2, 3),
                new Edge(1, 3, 6),
                new Edge(2, 3, 4),
                new Edge(2, 4, 2),
                new Edge(3, 4, 5)
        };
        System.out.println("Test Case 2: Larger Graph");
        kruskal.kruskalMST(vertices2, edges2);
        System.out.println();

        // Error case: Invalid input
        try {
            kruskal.kruskalMST(0, new Edge[]{});
        } catch (Exception e) {
            System.out.println("Error Case: " + e.getMessage());
        }
    }
}
