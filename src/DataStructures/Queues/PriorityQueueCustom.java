package DataStructures.Queues;

/**
 * A custom implementation of a Priority Queue in Java using a binary heap.
 * This priority queue supports adding elements with priorities, peeking at the highest priority element,
 * and removing the highest priority element. Higher priority values indicate higher priority.
 */
public class PriorityQueueCustom<T> {
    // Node class to store an element and its priority
    private static class Node<T> {
        T data;
        int priority;

        Node(T data, int priority) {
            this.data = data;
            this.priority = priority;
        }
    }

    // Array to store the heap
    private Node<T>[] heap;
    // Current size of the priority queue
    private int size;
    // Maximum capacity of the priority queue
    private int capacity;

    // Constructor to initialize the priority queue with a given capacity
    @SuppressWarnings("unchecked")
    public PriorityQueueCustom(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new Node[capacity];
    }

    // Add an element with a given priority to the queue
    public void add(T data, int priority) {
        if (size >= capacity) {
            throw new IllegalStateException("Priority Queue is full!");
        }

        // Create a new node and add it to the end of the heap
        Node<T> newNode = new Node<>(data, priority);
        heap[size] = newNode;
        size++;

        // Fix the heap property by moving the new element up
        heapifyUp(size - 1);
    }

    // Peek at the highest priority element without removing it
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Priority Queue is empty!");
        }
        return heap[0].data;
    }

    // Remove and return the highest priority element
    public T poll() {
        if (isEmpty()) {
            throw new IllegalStateException("Priority Queue is empty!");
        }

        T data = heap[0].data;
        // Replace root with the last element and reduce size
        heap[0] = heap[size - 1];
        heap[size - 1] = null;
        size--;

        // Fix the heap property by moving the root down
        if (size > 0) {
            heapifyDown(0);
        }

        return data;
    }

    // Check if the priority queue is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Get the current size of the priority queue
    public int size() {
        return size;
    }

    // Helper method to maintain heap property by moving an element up
    private void heapifyUp(int index) {
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            if (heap[parentIndex].priority < heap[index].priority) {
                // Swap with parent if parent's priority is lower
                swap(parentIndex, index);
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    // Helper method to maintain heap property by moving an element down
    private void heapifyDown(int index) {
        while (index < size) {
            int leftChild = 2 * index + 1;
            int rightChild = 2 * index + 2;
            int largest = index;

            // Compare with left child
            if (leftChild < size && heap[leftChild].priority > heap[largest].priority) {
                largest = leftChild;
            }
            // Compare with right child
            if (rightChild < size && heap[rightChild].priority > heap[largest].priority) {
                largest = rightChild;
            }

            if (largest != index) {
                // Swap with the child having higher priority
                swap(index, largest);
                index = largest;
            } else {
                break;
            }
        }
    }

    // Helper method to swap two elements in the heap
    private void swap(int i, int j) {
        Node<T> temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    // Main method to demonstrate the usage of PriorityQueueCustom
    public static void main(String[] args) {
        // Create a priority queue with capacity 5
        PriorityQueueCustom<String> pq = new PriorityQueueCustom<>(5);

        // Add tasks with priorities
        pq.add("Task 1: Write code", 2);
        pq.add("Task 2: Review code", 5);
        pq.add("Task 3: Test application", 1);
        pq.add("Task 4: Deploy to production", 10);
        pq.add("Task 5: Debug issue", 7);

        // Print size of the queue
        System.out.println("Priority Queue size: " + pq.size());

        // Process tasks in order of priority
        System.out.println("Processing tasks in order of priority:");
        while (!pq.isEmpty()) {
            System.out.println("Processing: " + pq.poll());
        }

        // Try to poll from an empty queue (will throw exception)
        try {
            pq.poll();
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}