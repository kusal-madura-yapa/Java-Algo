package DataStructures.Hashing;

public class HashTableOpenAddressing<K, V> {
    // Node class to store key-value pairs
    private static class Entry<K, V> {
        K key;
        V value;
        boolean isDeleted; // Flag for lazy deletion

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.isDeleted = false;
        }
    }

    // Array to store entries
    private Entry<K, V>[] table;
    private int size; // Number of key-value pairs
    private static final int DEFAULT_CAPACITY = 16; // Default table size
    private static final double LOAD_FACTOR = 0.75; // Threshold for resizing

    // Constructor to initialize the hash table
    @SuppressWarnings("unchecked")
    public HashTableOpenAddressing() {
        table = new Entry[DEFAULT_CAPACITY];
        size = 0;
    }

    // Hash function to compute initial index for a key
    private int hash(K key) {
        return Math.abs(key.hashCode() % table.length);
    }

    // Linear probing to find the next available slot or the key
    private int probe(K key, int i) {
        return (hash(key) + i) % table.length;
    }

    // Resize the table when load factor is exceeded
    @SuppressWarnings("unchecked")
    private void resize() {
        Entry<K, V>[] oldTable = table;
        table = new Entry[oldTable.length * 2];
        size = 0;
        // Rehash all non-deleted entries
        for (Entry<K, V> entry : oldTable) {
            if (entry != null && !entry.isDeleted) {
                put(entry.key, entry.value);
            }
        }
    }

    // Put a key-value pair into the hash table
    public void put(K key, V value) {
        if (key == null) return; // Null keys not allowed
        if ((double) (size + 1) / table.length > LOAD_FACTOR) {
            resize();
        }

        int i = 0;
        int index = probe(key, i);
        while (table[index] != null && !table[index].isDeleted && !table[index].key.equals(key)) {
            i++;
            index = probe(key, i);
        }

        // If slot is empty or marked deleted, add new entry
        if (table[index] == null || table[index].isDeleted) {
            table[index] = new Entry<>(key, value);
            size++;
        } else {
            // Key already exists, update value
            table[index].value = value;
        }
    }

    // Get the value associated with a key
    public V get(K key) {
        if (key == null) return null;
        int i = 0;
        int index = probe(key, i);
        while (table[index] != null) {
            if (!table[index].isDeleted && table[index].key.equals(key)) {
                return table[index].value;
            }
            i++;
            index = probe(key, i);
        }
        return null; // Key not found
    }

    // Remove a key-value pair from the hash table
    public boolean remove(K key) {
        if (key == null) return false;
        int i = 0;
        int index = probe(key, i);
        while (table[index] != null) {
            if (!table[index].isDeleted && table[index].key.equals(key)) {
                table[index].isDeleted = true; // Lazy deletion
                size--;
                return true;
            }
            i++;
            index = probe(key, i);
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
        HashTableOpenAddressing<String, Integer> ht = new HashTableOpenAddressing<>();

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
