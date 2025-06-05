package DataStructures.Trees;

/**
 * A class implementing a Red-Black Tree in Java.
 * A Red-Black Tree is a self-balancing binary search tree where each node has a color
 * (red or black) and follows specific properties to ensure balance. This implementation
 * includes insertion with balancing and an inorder traversal for verification.
 */
public class RedBlackTree {
    // Node class for the Red-Black Tree
    private static class Node {
        int data;
        Node left, right, parent;
        boolean isRed; // True for red, false for black

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
            this.parent = null;
            this.isRed = true; // New nodes are red by default
        }
    }

    // Sentinel node to represent null leaves (black)
    private final Node NIL;
    // Root of the Red-Black Tree
    private Node root;

    // Constructor to initialize the tree with a NIL sentinel
    public RedBlackTree() {
        NIL = new Node(0);
        NIL.isRed = false; // NIL nodes are black
        NIL.left = null;
        NIL.right = null;
        root = NIL;
    }

    // Insert a new value into the Red-Black Tree
    public void insert(int data) {
        Node node = new Node(data);
        node.left = NIL;
        node.right = NIL;
        root = insertRec(root, node);
        fixInsert(node);
    }

    // Recursive helper method for insertion (standard BST insert)
    private Node insertRec(Node root, Node node) {
        if (root == NIL) {
            return node;
        }
        if (node.data < root.data) {
            root.left = insertRec(root.left, node);
            root.left.parent = root;
        } else if (node.data > root.data) {
            root.right = insertRec(root.right, node);
            root.right.parent = root;
        }
        return root;
    }

    // Fix the Red-Black Tree properties after insertion
    private void fixInsert(Node node) {
        while (node != root && node.parent.isRed) {
            if (node.parent == node.parent.parent.left) {
                Node uncle = node.parent.parent.right;
                if (uncle.isRed) {
                    // Case 1: Uncle is red, recolor and move up
                    node.parent.isRed = false;
                    uncle.isRed = false;
                    node.parent.parent.isRed = true;
                    node = node.parent.parent;
                } else {
                    // Case 2: Uncle is black, node is right child
                    if (node == node.parent.right) {
                        node = node.parent;
                        leftRotate(node);
                    }
                    // Case 3: Uncle is black, node is left child
                    node.parent.isRed = false;
                    node.parent.parent.isRed = true;
                    rightRotate(node.parent.parent);
                }
            } else {
                Node uncle = node.parent.parent.left;
                if (uncle.isRed) {
                    // Case 1: Uncle is red, recolor and move up
                    node.parent.isRed = false;
                    uncle.isRed = false;
                    node.parent.parent.isRed = true;
                    node = node.parent.parent;
                } else {
                    // Case 2: Uncle is black, node is left child
                    if (node == node.parent.left) {
                        node = node.parent;
                        rightRotate(node);
                    }
                    // Case 3: Uncle is black, node is right child
                    node.parent.isRed = false;
                    node.parent.parent.isRed = true;
                    leftRotate(node.parent.parent);
                }
            }
        }
        root.isRed = false; // Root is always black
    }

    // Left rotation to balance the tree
    private void leftRotate(Node x) {
        Node y = x.right;
        x.right = y.left;
        if (y.left != NIL) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    // Right rotation to balance the tree
    private void rightRotate(Node y) {
        Node x = y.left;
        y.left = x.right;
        if (x.right != NIL) {
            x.right.parent = y;
        }
        x.parent = y.parent;
        if (y.parent == null) {
            root = x;
        } else if (y == y.parent.right) {
            y.parent.right = x;
        } else {
            y.parent.left = x;
        }
        x.right = y;
        y.parent = x;
    }

    // Inorder traversal to print the tree (Left -> Root -> Right)
    public void inorder() {
        System.out.print("Inorder Traversal: ");
        inorderRec(root);
        System.out.println();
    }

    private void inorderRec(Node node) {
        if (node != NIL) {
            inorderRec(node.left);
            System.out.print(node.data + "(" + (node.isRed ? "R" : "B") + ") ");
            inorderRec(node.right);
        }
    }

    // Main method to demonstrate Red-Black Tree operations
    public static void main(String[] args) {
        RedBlackTree tree = new RedBlackTree();
        int[] values = {10, 20, 30, 15, 25, 5, 1};
        System.out.println("Inserting values: 10, 20, 30, 15, 25, 5, 1");
        for (int value : values) {
            tree.insert(value);
        }
        tree.inorder();
    }
}