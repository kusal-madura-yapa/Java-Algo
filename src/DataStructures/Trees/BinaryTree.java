package DataStructures.Trees;

/**
 * A class implementing a Binary Tree in Java.
 * This implementation includes a Node class for the tree structure and methods
 * for inserting nodes and performing inorder, preorder, and postorder traversals.
 * Includes a main method with an example for clarity.
 */
public class BinaryTree {
    // Node class for the binary tree
    private static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    // Root of the binary tree
    private Node root;

    // Constructor to initialize an empty binary tree
    public BinaryTree() {
        this.root = null;
    }

    // Insert a new value into the binary tree
    public void insert(int data) {
        root = insertRec(root, data);
    }

    // Recursive helper method to insert a new value
    private Node insertRec(Node root, int data) {
        // If tree is empty or we reach a leaf, create a new node
        if (root == null) {
            root = new Node(data);
            return root;
        }

        // Recursively insert into left or right subtree
        if (data < root.data) {
            root.left = insertRec(root.left, data);
        } else if (data > root.data) {
            root.right = insertRec(root.right, data);
        }
        // If data equals root.data, do nothing (no duplicates)
        return root;
    }

    // Inorder traversal: Left -> Root -> Right
    public void inorder() {
        System.out.print("Inorder Traversal: ");
        inorderRec(root);
        System.out.println();
    }

    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.data + " ");
            inorderRec(root.right);
        }
    }

    // Preorder traversal: Root -> Left -> Right
    public void preorder() {
        System.out.print("Preorder Traversal: ");
        preorderRec(root);
        System.out.println();
    }

    private void preorderRec(Node root) {
        if (root != null) {
            System.out.print(root.data + " ");
            preorderRec(root.left);
            preorderRec(root.right);
        }
    }

    // Postorder traversal: Left -> Right -> Root
    public void postorder() {
        System.out.print("Postorder Traversal: ");
        postorderRec(root);
        System.out.println();
    }

    private void postorderRec(Node root) {
        if (root != null) {
            postorderRec(root.left);
            postorderRec(root.right);
            System.out.print(root.data + " ");
        }
    }

    // Main method to demonstrate the binary tree operations
    public static void main(String[] args) {
        // Create a binary tree instance
        BinaryTree tree = new BinaryTree();

        // Insert some values
        int[] values = {50, 30, 70, 20, 40, 60, 80};
        System.out.println("Inserting values: 50, 30, 70, 20, 40, 60, 80");
        for (int value : values) {
            tree.insert(value);
        }

        // Perform traversals
        tree.inorder();
        tree.preorder();
        tree.postorder();

        // Example of an empty tree
        BinaryTree emptyTree = new BinaryTree();
        System.out.println("\nTraversals of an empty tree:");
        emptyTree.inorder();
        emptyTree.preorder();
        emptyTree.postorder();
    }
}