package DataStructures.Heaps;

public class MaxHeap {
    // Array to store the heap elements
    private int[] heap;
    private int size;
    private int capacity;

    // Constructor to initialize the max heap with a given capacity
    public MaxHeap(int capacity) {
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

    // Heapify the subtree rooted at index to maintain max heap property
    private void heapify(int index) {
        int largest = index;
        int left = leftChild(index);
        int right = rightChild(index);

        // Compare with left child
        if (left < size && heap[left] > heap[largest]) {
            largest = left;
        }

        // Compare with right child
        if (right < size && heap[right] > heap[largest]) {
            largest = right;
        }

        // If largest is not the current node, swap and continue heapifying
        if (largest != index) {
            swap(index, largest);
            heapify(largest);
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

        // Bubble up to restore max heap property
        while (current > 0 && heap[current] > heap[parent(current)]) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    // Get the maximum element (root) without removing it
    public int peek() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty");
        }
        return heap[0];
    }

    // Remove and return the maximum element (root)
    public int extractMax() {
        if (size == 0) {
            throw new IllegalStateException("Heap is empty");
        }

        int max = heap[0];
        heap[0] = heap[size - 1];
        size--;
        heapify(0); // Restore max heap property
        return max;
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
        MaxHeap maxHeap = new MaxHeap(10);

        // Insert some elements
        maxHeap.insert(5);
        maxHeap.insert(3);
        maxHeap.insert(17);
        maxHeap.insert(10);
        maxHeap.insert(84);
        maxHeap.insert(19);
        maxHeap.insert(6);

        System.out.println("Heap size: " + maxHeap.size()); // Heap size: 7
        System.out.println("Max element: " + maxHeap.peek()); // Max element: 84

        // Extract and print elements in descending order
        System.out.println("Extracting elements:");
        while (!maxHeap.isEmpty()) {
            System.out.println("Extracted max: " + maxHeap.extractMax());
        }
        // Expected order: 84, 19, 17, 10, 6, 5, 3
    }
}
