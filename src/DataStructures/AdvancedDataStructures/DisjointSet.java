package DataStructures.AdvancedDataStructures;

public class DisjointSet {
    // Arrays to store parent pointers and rank of each element
    private int[] parent;
    private int[] rank;

    // Constructor to initialize the Disjoint Set with a given size
    public DisjointSet(int size) {
        parent = new int[size];
        rank = new int[size];
        // Initially, each element is its own set (parent points to itself)
        for (int i = 0; i < size; i++) {
            makeSet(i);
        }
    }

    // Create a new set with a single element
    public void makeSet(int x) {
        parent[x] = x; // Each element is its own parent initially
        rank[x] = 0;   // Initial rank is 0
    }

    // Find the representative (root) of the set that x belongs to
    // Uses path compression for optimization
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // Path compression: point directly to root
        }
        return parent[x];
    }

    // Unite two sets using union by rank for efficiency
    public void union(int x, int y) {
        int rootX = find(x); // Find root of x
        int rootY = find(y); // Find root of y

        if (rootX == rootY) {
            return; // Already in the same set
        }

        // Attach smaller rank tree under the larger rank tree
        if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            // If ranks are equal, arbitrarily choose one as root and increment rank
            parent[rootY] = rootX;
            rank[rootX]++;
        }
    }

    // Check if two elements are in the same set
    public boolean isConnected(int x, int y) {
        return find(x) == find(y);
    }

    // Example usage
    public static void main(String[] args) {
        // Create a DisjointSet with 5 elements (0 to 4)
        DisjointSet ds = new DisjointSet(5);

        // Perform some union operations
        ds.union(0, 1); // Connect 0 and 1
        ds.union(1, 2); // Connect 1 and 2 (so 0, 1, 2 are in one set)
        ds.union(3, 4); // Connect 3 and 4

        // Check connectivity
        System.out.println("Are 0 and 2 connected? " + ds.isConnected(0, 2)); // true
        System.out.println("Are 0 and 3 connected? " + ds.isConnected(0, 3)); // false
        System.out.println("Are 3 and 4 connected? " + ds.isConnected(3, 4)); // true

        // Perform another union
        ds.union(2, 3); // Connect sets: now 0, 1, 2, 3, 4 are all connected
        System.out.println("After union(2, 3):");
        System.out.println("Are 0 and 4 connected? " + ds.isConnected(0, 4)); // true
        System.out.println("Are 1 and 3 connected? " + ds.isConnected(1, 3)); // true
    }
}
