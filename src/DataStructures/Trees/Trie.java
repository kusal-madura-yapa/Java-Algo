package DataStructures.Trees;

public class Trie {
    // Node class for the Trie
    private static class TrieNode {
        TrieNode[] children;
        boolean isEndOfWord;

        TrieNode() {
            children = new TrieNode[26]; // Assuming lowercase English alphabet
            isEndOfWord = false;
        }
    }

    private TrieNode root;

    // Constructor to initialize an empty Trie
    public Trie() {
        root = new TrieNode();
    }

    // Insert a word into the Trie
    public void insert(String word) {
        TrieNode current = root;
        for (char ch : word.toLowerCase().toCharArray()) {
            int index = ch - 'a';
            if (index < 0 || index >= 26) {
                throw new IllegalArgumentException("Word contains invalid characters: " + word);
            }
            if (current.children[index] == null) {
                current.children[index] = new TrieNode();
            }
            current = current.children[index];
        }
        current.isEndOfWord = true;
    }

    // Search for a word in the Trie
    public boolean search(String word) {
        TrieNode node = searchNode(word);
        return node != null && node.isEndOfWord;
    }

    // Check if there is any word in the Trie that starts with the given prefix
    public boolean startsWith(String prefix) {
        return searchNode(prefix) != null;
    }

    // Helper method to search for a node corresponding to a string
    private TrieNode searchNode(String str) {
        TrieNode current = root;
        for (char ch : str.toLowerCase().toCharArray()) {
            int index = ch - 'a';
            if (index < 0 || index >= 26) {
                return null;
            }
            if (current.children[index] == null) {
                return null;
            }
            current = current.children[index];
        }
        return current;
    }

    // Delete a word from the Trie
    public void delete(String word) {
        deleteRec(root, word, 0);
    }

    // Recursive helper method for deletion
    private boolean deleteRec(TrieNode current, String word, int index) {
        if (index == word.length()) {
            if (!current.isEndOfWord) {
                return false;
            }
            current.isEndOfWord = false;
            return isEmpty(current);
        }
        int charIndex = word.charAt(index) - 'a';
        if (charIndex < 0 || charIndex >= 26 || current.children[charIndex] == null) {
            return false;
        }
        boolean shouldDeleteChild = deleteRec(current.children[charIndex], word, index + 1);
        if (shouldDeleteChild) {
            current.children[charIndex] = null;
            return isEmpty(current) && !current.isEndOfWord;
        }
        return false;
    }

    // Check if a node has no children
    private boolean isEmpty(TrieNode node) {
        for (TrieNode child : node.children) {
            if (child != null) {
                return false;
            }
        }
        return true;
    }

    // Example usage
    public static void main(String[] args) {
        Trie trie = new Trie();

        // Insert words
        trie.insert("apple");
        trie.insert("app");
        trie.insert("banana");

        // Search for words
        System.out.println("Search 'apple': " + trie.search("apple")); // true
        System.out.println("Search 'app': " + trie.search("app"));     // true
        System.out.println("Search 'ban': " + trie.search("ban"));     // false

        // Check prefixes
        System.out.println("Starts with 'app': " + trie.startsWith("app")); // true
        System.out.println("Starts with 'ban': " + trie.startsWith("ban")); // true
        System.out.println("Starts with 'cat': " + trie.startsWith("cat")); // false

        // Delete words
        trie.delete("app");
        System.out.println("After deleting 'app':");
        System.out.println("Search 'app': " + trie.search("app"));     // false
        System.out.println("Search 'apple': " + trie.search("apple")); // true
    }
}

