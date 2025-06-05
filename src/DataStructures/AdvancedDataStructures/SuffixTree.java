package DataStructures.AdvancedDataStructures;

public class SuffixTree {
    // Node class for the suffix tree
    private static class Node {
        int start; // Start index of the substring in the text
        int end;   // End index of the substring in the text
        Node[] children; // Array of child nodes (for each possible character)
        Node suffixLink; // Suffix link for Ukkonen's algorithm
        int index; // Stores the starting index of the suffix (for leaf nodes)

        Node(int start, int end) {
            this.start = start;
            this.end = end;
            this.children = new Node[26]; // Assuming lowercase English alphabet (a-z)
            this.suffixLink = null;
            this.index = -1; // -1 for internal nodes, set for leaf nodes
        }
    }

    private String text; // The input string
    private Node root;   // Root of the suffix tree
    private int remaining; // Number of suffixes yet to be added
    private Node activeNode; // Active node for Ukkonen's algorithm
    private int activeEdge; // Starting index of the active edge
    private int activeLength; // Length of the active edge
    private int leafEnd; // End index for all leaf nodes (grows with each phase)

    // Constructor to build the suffix tree for the given string
    public SuffixTree(String text) {
        this.text = text + "$"; // Append sentinel character to ensure unique suffixes
        this.root = new Node(-1, -1);
        this.activeNode = root;
        this.activeEdge = -1;
        this.activeLength = 0;
        this.leafEnd = -1;
        this.remaining = 0;
        buildSuffixTree();
    }

    // Build the suffix tree using Ukkonen's algorithm
    private void buildSuffixTree() {
        int n = text.length();
        // Process each character in the text
        for (int i = 0; i < n; i++) {
            leafEnd = i; // Update end of all leaf nodes
            remaining++; // One more suffix to add
            Node lastCreatedNode = null;

            // Process all suffixes that need to be added
            while (remaining > 0) {
                if (activeLength == 0) {
                    activeEdge = i; // Start of the current edge
                }

                // Check if active node has a child for the current character
                int chIndex = text.charAt(activeEdge) - 'a'; // Adjust for 'a' base
                if (chIndex < 0 || chIndex >= 26) chIndex = 25; // Map '$' to last index

                if (activeNode.children[chIndex] == null) {
                    // No child exists: create a leaf node
                    activeNode.children[chIndex] = new Node(i, leafEnd);
                    activeNode.children[chIndex].index = i;
                    if (activeNode != root) {
                        activeNode.suffixLink = root;
                    }
                } else {
                    // Child exists: walk down the edge
                    Node next = activeNode.children[chIndex];
                    int edgeLength = next.end - next.start + 1;
                    if (activeLength >= edgeLength) {
                        // Skip down the edge
                        activeEdge += edgeLength;
                        activeLength -= edgeLength;
                        activeNode = next;
                        continue;
                    }

                    // Check if the next character matches
                    if (text.charAt(next.start + activeLength) == text.charAt(i)) {
                        activeLength++;
                        if (activeNode != root) {
                            activeNode.suffixLink = root;
                        }
                        break;
                    }

                    // Split the edge: create an internal node
                    Node split = new Node(next.start, next.start + activeLength - 1);
                    activeNode.children[chIndex] = split;
                    split.children[text.charAt(i) - 'a'] = new Node(i, leafEnd);
                    split.children[text.charAt(i) - 'a'].index = i;
                    next.start += activeLength;
                    split.children[text.charAt(next.start) - 'a'] = next;

                    if (lastCreatedNode != null) {
                        lastCreatedNode.suffixLink = split;
                    }
                    lastCreatedNode = split;
                }

                remaining--;
                if (activeNode == root && activeLength > 0) {
                    activeLength--;
                    activeEdge = i - remaining + 1;
                } else if (activeNode != root) {
                    activeNode = activeNode.suffixLink;
                }
            }
        }
    }

    // Search for a pattern in the suffix tree
    public boolean search(String pattern) {
        Node current = root;
        int i = 0;
        while (i < pattern.length()) {
            int chIndex = pattern.charAt(i) - 'a';
            if (chIndex < 0 || chIndex >= 26) chIndex = 25; // Map '$' to last index
            if (current.children[chIndex] == null) {
                return false; // No path for this character
            }
            Node node = current.children[chIndex];
            int edgeLength = node.end - node.start + 1;
            for (int j = 0; j < edgeLength && i < pattern.length(); j++) {
                if (text.charAt(node.start + j) != pattern.charAt(i)) {
                    return false;
                }
                i++;
            }
            current = node;
        }
        return true; // Pattern found
    }

    // Print the suffix tree for visualization
    private void printTree(Node node, int level) {
        for (int i = 0; i < 26; i++) {
            if (node.children[i] != null) {
                Node child = node.children[i];
                String edgeLabel = text.substring(child.start, child.end + 1);
                System.out.println("  ".repeat(level) + "Edge: " + edgeLabel +
                        " (start: " + child.start + ", end: " + child.end +
                        ", suffix index: " + child.index + ")");
                printTree(child, level + 1);
            }
        }
    }

    // Public method to print the suffix tree
    public void print() {
        System.out.println("Suffix Tree for: " + text);
        printTree(root, 0);
    }

    // Example usage
    public static void main(String[] args) {
        // Example string
        String text = "banana";
        System.out.println("Building suffix tree for: " + text);

        // Create suffix tree
        SuffixTree st = new SuffixTree(text);

        // Print the tree
        st.print();

        // Test pattern searches
        String[] patterns = {"ana", "nana", "xyz", "a"};
        for (String pattern : patterns) {
            System.out.println("\nSearching for pattern: " + pattern);
            boolean found = st.search(pattern);
            System.out.println("Pattern '" + pattern + "' found: " + found);
        }
    }
}
