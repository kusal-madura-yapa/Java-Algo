package DataStructures.AdvancedDataStructures;

public class FenwickTree {
    // Array to store the Fenwick Tree
    private int[] tree;
    private int size;

    // Constructor to initialize the Fenwick Tree with a given size
    public FenwickTree(int size) {
        this.size = size;
        tree = new int[size + 1]; // 1-based indexing for Fenwick Tree
    }

    // Constructor to initialize the Fenwick Tree from an array
    public FenwickTree(int[] array) {
        this.size = array.length;
        tree = new int[size + 1]; // 1-based indexing
        // Build the tree by performing updates for each element
        for (int i = 0; i < array.length; i++) {
            update(i, array[i]);
        }
    }

    // Update the tree by adding 'delta' to the index 'idx' and its affected ranges
    public void update(int idx, int delta) {
        // Increment index by 1 for 1-based indexing
        idx++;
        // Update all indices responsible for this position
        while (idx <= size) {
            tree[idx] += delta;
            // Move to the next relevant index using least significant bit
            idx += idx & (-idx);
        }
    }

    // Compute the prefix sum from index 0 to idx (inclusive)
    private int prefixSum(int idx) {
        int sum = 0;
        // Increment index by 1 for 1-based indexing
        idx++;
        // Add values from tree while moving to parent nodes
        while (idx > 0) {
            sum += tree[idx];
            // Move to parent by removing least significant bit
            idx -= idx & (-idx);
        }
        return sum;
    }

    // Compute the range sum from index 'left' to 'right' (inclusive)
    public int rangeSum(int left, int right) {
        if (left == 0) {
            return prefixSum(right);
        }
        return prefixSum(right) - prefixSum(left - 1);
    }

    // Example usage
    public static void main(String[] args) {
        // Example array
        int[] array = {1, 3, 5, 7, 9, 11};
        System.out.println("Original array: [1, 3, 5, 7, 9, 11]");

        // Create Fenwick Tree from the array
        FenwickTree ft = new FenwickTree(array);

        // Test prefix sum
        System.out.println("Prefix sum from 0 to 2: " + ft.prefixSum(2)); // 1 + 3 + 5 = 9
        System.out.println("Prefix sum from 0 to 5: " + ft.prefixSum(5)); // 1 + 3 + 5 + 7 + 9 + 11 = 36

        // Test range sum
        System.out.println("Range sum from 1 to 4: " + ft.rangeSum(1, 4)); // 3 + 5 + 7 + 9 = 24

        // Update: add 10 to index 3 (value 7 becomes 17)
        ft.update(3, 10);
        System.out.println("After update: add 10 to index 3");
        System.out.println("Prefix sum from 0 to 5: " + ft.prefixSum(5)); // 1 + 3 + 5 + 17 + 9 + 11 = 46
        System.out.println("Range sum from 1 to 4: " + ft.rangeSum(1, 4)); // 3 + 5 + 17 + 9 = 34
    }
}