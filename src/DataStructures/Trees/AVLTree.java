package DataStructures.Trees;

public class AVLTree {
    // Node class for the AVL tree
    private static class Node {
        int key;
        int height;
        Node left;
        Node right;

        Node(int key) {
            this.key = key;
            this.height = 1;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;

    // Constructor to initialize an empty AVL tree
    public AVLTree() {
        root = null;
    }

    // Get the height of a node
    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    // Get the balance factor of a node
    private int getBalance(Node node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    // Update the height of a node
    private void updateHeight(Node node) {
        if (node != null) {
            node.height = Math.max(height(node.left), height(node.right)) + 1;
        }
    }

    // Right rotation
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    // Left rotation
    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        updateHeight(x);
        updateHeight(y);
        return y;
    }

    // Insert a key into the AVL tree
    public void insert(int key) {
        root = insertRec(root, key);
    }

    // Recursive helper method for insertion
    private Node insertRec(Node node, int key) {
        // Standard BST insertion
        if (node == null) {
            return new Node(key);
        }
        if (key < node.key) {
            node.left = insertRec(node.left, key);
        } else if (key > node.key) {
            node.right = insertRec(node.right, key);
        } else {
            return node; // Duplicate keys not allowed
        }

        // Update height of current node
        updateHeight(node);

        // Get balance factor
        int balance = getBalance(node);

        // Left Left Case
        if (balance > 1 && key < node.left.key) {
            return rightRotate(node);
        }
        // Right Right Case
        if (balance < -1 && key > node.right.key) {
            return leftRotate(node);
        }
        // Left Right Case
        if (balance > 1 && key > node.left.key) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // Right Left Case
        if (balance < -1 && key < node.right.key) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // Find the node with the minimum value in a subtree
    private Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    // Delete a key from the AVL tree
    public void delete(int key) {
        root = deleteRec(root, key);
    }

    // Recursive helper method for deletion
    private Node deleteRec(Node node, int key) {
        // Standard BST deletion
        if (node == null) {
            return null;
        }
        if (key < node.key) {
            node.left = deleteRec(node.left, key);
        } else if (key > node.key) {
            node.right = deleteRec(node.right, key);
        } else {
            // Node with only one child or no child
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }
            // Node with two children
            Node temp = minValueNode(node.right);
            node.key = temp.key;
            node.right = deleteRec(node.right, temp.key);
        }

        // Update height of current node
        updateHeight(node);

        // Get balance factor
        int balance = getBalance(node);

        // Left Left Case
        if (balance > 1 && getBalance(node.left) >= 0) {
            return rightRotate(node);
        }
        // Left Right Case
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }
        // Right Right Case
        if (balance < -1 && getBalance(node.right) <= 0) {
            return leftRotate(node);
        }
        // Right Left Case
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // Inorder traversal to print the tree
    private void inorderRec(Node node) {
        if (node != null) {
            inorderRec(node.left);
            System.out.print(node.key + " ");
            inorderRec(node.right);
        }
    }

    // Public method to print the tree
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
        AVLTree tree = new AVLTree();

        // Insert elements
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);
        tree.insert(50);
        tree.insert(25);
        tree.printInorder(); // Inorder traversal: 10 20 25 30 40 50

        // Delete elements
        tree.delete(30);
        tree.printInorder(); // Inorder traversal: 10 20 25 40 50

        // Insert more elements
        tree.insert(15);
        tree.insert(5);
        tree.printInorder(); // Inorder traversal: 5 10 15 20 25 40 50
    }
}

