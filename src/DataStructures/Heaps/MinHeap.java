package DataStructures.Heaps;

public class MinHeap {
    // Array to store the heap elements
    private int[] heap;
    private int size;
    private int capacity;

    // Constructor to initialize the min heap with a given capacity
    public MinHeap(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity];
    }

    // Get the index of the parent of a node
    private int parent(int index) {
        return (index - 1) / 2;
    }

    // Get the index of the left child of a node
    private int leftChild(int index) {
        return 2 * index + 1;
    }

    // Get the index of the right child of a node
    private int rightChild(int index) {
        return 2 * index + 2;
    }

    // Swap two elements in the heap
    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // Heapify the subtree rooted at index to maintain min heap property
    private void heapify(int index) {
        int smallest = index;
        int left = leftChild(index);
        int right = rightChild(index);

        // Compare with left child
        if (left < size && heap[left] < heap[smallest]) {
            smallest = left;
        }

        // Compare with right child
        if (right < size && heap[right] < heap[smallest]) {
            smallest = right;
        }

        // If smallest is not the current node, swap and continue heapifying
        if (smallest != index) {
            swap(index, smallest);
            heapify(smallest);
        }
    }

    // Insert a new element into the heap
    public void insert(int value) {
        if (size >= capacity) {
            throw new IllegalStateException("Heap is full");
        }

        // Add the new element at the end
        heap[size] = value;
        int current = size;
        size++;

        // Bubble up to restore min heap property
        while (current > 0 && heap[current] < heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    // Get the minimum element (root) without removing it
    public int peek() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty");
        }
        return heap[0];
    }

    // Remove and return the minimum element (root)
    public int extractMin() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty");
        }

        int min = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapify(0); // Restore min heap property
        return min;
    }

    // Get the current size of the heap
    public int size() {
        return size;
    }

    // Check if the heap is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Example usage
    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap(10);

        // Insert some elements
        minHeap.insert(5);
        minHeap.insert(3);
        minHeap.insert(17);
        minHeap.insert(10);
        minHeap.insert(84);
        minHeap.insert(19);
        minHeap.insert(6);

        System.out.println("Heap size: " + minHeap.size()); // Heap size: 7
        System.out.println("Min element: " + minHeap.peek()); // Min element: 3

        // Extract and print elements in ascending order
        System.out.println("Extracting elements:");
        while (!minHeap.isEmpty()) {
            System.out.println("Extracted min: " + minHeap.extractMin());
        }
        // Expected order: 3, 5, 6, 10, 17, 19, 84
    }
}
