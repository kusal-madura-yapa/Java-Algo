package DataStructures.Trees;

public class BinarySearchTree {
    // Node class for the binary search tree
    private static class Node {
        int key;
        Node left;
        Node right;

        Node(int key) {
            this.key = key;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;

    // Constructor to initialize an empty binary search tree
    public BinarySearchTree() {
        root = null;
    }

    // Insert a key into the BST
    public void insert(int key) {
        root = insertRec(root, key);
    }

    // Recursive helper method for insertion
    private Node insertRec(Node root, int key) {
        if (root == null) {
            return new Node(key);
        }
        if (key < root.key) {
            root.left = insertRec(root.left, key);
        } else if (key > root.key) {
            root.right = insertRec(root.right, key);
        }
        return root; // Duplicate keys are ignored
    }

    // Search for a key in the BST
    public boolean search(int key) {
        return searchRec(root, key);
    }

    // Recursive helper method for search
    private boolean searchRec(Node root, int key) {
        if (root == null || root.key == key) {
            return root != null;
        }
        if (key < root.key) {
            return searchRec(root.left, key);
        }
        return searchRec(root.right, key);
    }

    // Delete a key from the BST
    public void delete(int key) {
        root = deleteRec(root, key);
    }

    // Recursive helper method for deletion
    private Node deleteRec(Node root, int key) {
        if (root == null) {
            return null;
        }
        if (key < root.key) {
            root.left = deleteRec(root.left, key);
        } else if (key > root.key) {
            root.right = deleteRec(root.right, key);
        } else {
            // Node with only one child or no child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            // Node with two children: get the inorder successor (smallest in right subtree)
            Node minNode = minValueNode(root.right);
            root.key = minNode.key;
            root.right = deleteRec(root.right, minNode.key);
        }
        return root;
    }

    // Find the node with the minimum value in a subtree
    private Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    // Inorder traversal to print the tree
    private void inorderRec(Node root) {
        if (root != null) {
            inorderRec(root.left);
            System.out.print(root.key + " ");
            inorderRec(root.right);
        }
    }

    // Public method to print the tree in inorder
    public void printInorder() {
        if (root == null) {
            System.out.println("Tree is empty");
            return;
        }
        System.out.print("Inorder traversal: ");
        inorderRec(root);
        System.out.println();
    }

    // Example usage
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        // Insert elements
        bst.insert(50);
        bst.insert(30);
        bst.insert(70);
        bst.insert(20);
        bst.insert(40);
        bst.insert(60);
        bst.insert(80);
        bst.printInorder(); // Inorder traversal: 20 30 40 50 60 70 80

        // Search for elements
        System.out.println("Search 40: " + bst.search(40)); // Search 40: true
        System.out.println("Search 90: " + bst.search(90)); // Search 90: false

        // Delete elements
        bst.delete(20);
        bst.printInorder(); // Inorder traversal: 30 40 50 60 70 80
        bst.delete(30);
        bst.printInorder(); // Inorder traversal: 40 50 60 70 80
        bst.delete(50);
        bst.printInorder(); // Inorder traversal: 40 60 70 80
    }
}