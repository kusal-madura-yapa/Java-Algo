package DataStructures.AdvancedDataStructures;

public class SegmentTree {
    // Array to store the segment tree nodes
    private int[] tree;
    // Size of the input array
    private int n;

    // Constructor to build the segment tree from an input array
    public SegmentTree(int[] arr) {
        n = arr.length;
        // Tree size is approximately 4*n to accommodate all nodes
        tree = new int[4 * n];
        buildTree(arr, 0, 0, n - 1);
    }

    // Build the segment tree recursively
    private void buildTree(int[] arr, int treeIndex, int left, int right) {
        // Base case: if leaf node, store the array value
        if (left == right) {
            tree[treeIndex] = arr[left];
            return;
        }
        int mid = left + (right - left) / 2;
        // Recursively build left and right subtrees
        buildTree(arr, 2 * treeIndex + 1, left, mid);
        buildTree(arr, 2 * treeIndex + 2, mid + 1, right);
        // Internal node stores the sum of its children
        tree[treeIndex] = tree[2 * treeIndex + 1] + tree[2 * treeIndex + 2];
    }

    // Update the value at index 'idx' to 'val' and adjust the tree
    public void update(int idx, int val) {
        updateTree(0, 0, n - 1, idx, val);
    }

    // Recursively update the tree
    private void updateTree(int treeIndex, int left, int right, int idx, int val) {
        // Base case: leaf node, update the value
        if (left == right) {
            tree[treeIndex] = val;
            return;
        }
        int mid = left + (right - left) / 2;
        // Recurse to the left or right subtree based on index
        if (idx <= mid) {
            updateTree(2 * treeIndex + 1, left, mid, idx, val);
        } else {
            updateTree(2 * treeIndex + 2, mid + 1, right, idx, val);
        }
        // Update internal node with sum of children
        tree[treeIndex] = tree[2 * treeIndex + 1] + tree[2 * treeIndex + 2];
    }

    // Query the sum of elements in range [queryLeft, queryRight]
    public int query(int queryLeft, int queryRight) {
        return queryTree(0, 0, n - 1, queryLeft, queryRight);
    }

    // Recursively compute the sum for the given range
    private int queryTree(int treeIndex, int left, int right, int queryLeft, int queryRight) {
        // If the current range is completely outside the query range
        if (left > queryRight || right < queryLeft) {
            return 0;
        }
        // If the current range is completely inside the query range
        if (queryLeft <= left && right <= queryRight) {
            return tree[treeIndex];
        }
        // Partial overlap: recurse on both subtrees
        int mid = left + (right - left) / 2;
        int leftSum = queryTree(2 * treeIndex + 1, left, mid, queryLeft, queryRight);
        int rightSum = queryTree(2 * treeIndex + 2, mid + 1, right, queryLeft, queryRight);
        return leftSum + rightSum;
    }

    // Example usage
    public static void main(String[] args) {
        // Example array
        int[] arr = {1, 3, 5, 7, 9, 11};
        System.out.println("Original array: [1, 3, 5, 7, 9, 11]");

        // Create segment tree
        SegmentTree st = new SegmentTree(arr);

        // Test range sum queries
        System.out.println("Sum from index 1 to 4: " + st.query(1, 4)); // 3 + 5 + 7 + 9 = 24
        System.out.println("Sum from index 0 to 5: " + st.query(0, 5)); // 1 + 3 + 5 + 7 + 9 + 11 = 36

        // Update: change value at index 3 to 10
        st.update(3, 10);
        System.out.println("After update: change index 3 to 10");
        System.out.println("Sum from index 1 to 4: " + st.query(1, 4)); // 3 + 5 + 10 + 9 = 27
        System.out.println("Sum from index 0 to 5: " + st.query(0, 5)); // 1 + 3 + 5 + 10 + 9 + 11 = 39
    }
}