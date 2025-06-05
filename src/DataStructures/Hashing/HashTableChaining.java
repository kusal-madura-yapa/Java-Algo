package DataStructures.Hashing;

import java.util.LinkedList;

public class HashTableChaining<K, V> {
    // Node class to store key-value pairs
    private static class Entry<K, V> {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    // Array of linked lists for chaining
    private LinkedList<Entry<K, V>>[] table;
    private int size; // Number of key-value pairs
    private static final int DEFAULT_CAPACITY = 16; // Default table size

    // Constructor to initialize the hash table
    @SuppressWarnings("unchecked")
    public HashTableChaining() {
        table = new LinkedList[DEFAULT_CAPACITY];
        size = 0;
        for (int i = 0; i < table.length; i++) {
            table[i] = new LinkedList<>();
        }
    }

    // Hash function to compute index for a key
    private int hash(K key) {
        return Math.abs(key.hashCode() % table.length);
    }

    // Put a key-value pair into the hash table
    public void put(K key, V value) {
        int index = hash(key);
        LinkedList<Entry<K, V>> chain = table[index];

        // Check if key already exists, update value if so
        for (Entry<K, V> entry : chain) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }

        // Key not found, add new entry
        chain.add(new Entry<>(key, value));
        size++;
    }

    // Get the value associated with a key
    public V get(K key) {
        int index = hash(key);
        LinkedList<Entry<K, V>> chain = table[index];

        // Search the chain for the key
        for (Entry<K, V> entry : chain) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null; // Key not found
    }

    // Remove a key-value pair from the hash table
    public boolean remove(K key) {
        int index = hash(key);
        LinkedList<Entry<K, V>> chain = table[index];

        // Search the chain and remove the entry if found
        for (Entry<K, V> entry : chain) {
            if (entry.key.equals(key)) {
                chain.remove(entry);
                size--;
                return true;
            }
        }
        return false; // Key not found
    }

    // Get the number of key-value pairs
    public int size() {
        return size;
    }

    // Check if the hash table is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Example usage
    public static void main(String[] args) {
        HashTableChaining<String, Integer> ht = new HashTableChaining<>();

        // Add some key-value pairs
        ht.put("apple", 1);
        ht.put("banana", 2);
        ht.put("orange", 3);
        ht.put("apple", 5); // Update value for "apple"

        System.out.println("Size: " + ht.size()); // Size: 3
        System.out.println("Get 'apple': " + ht.get("apple")); // Get 'apple': 5
        System.out.println("Get 'banana': " + ht.get("banana")); // Get 'banana': 2
        System.out.println("Get 'grape': " + ht.get("grape")); // Get 'grape': null

        // Remove a key
        System.out.println("Remove 'banana': " + ht.remove("banana")); // Remove 'banana': true
        System.out.println("Remove 'grape': " + ht.remove("grape")); // Remove 'grape': false
        System.out.println("Size after removal: " + ht.size()); // Size after removal: 2
        System.out.println("Is empty? " + ht.isEmpty()); // Is empty? false
    }
}