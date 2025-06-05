package DataStructures.Queues;

public class ArrayQueue<T> {
    // Array to store queue elements
    private T[] queue;
    private int front; // Index of the front element
    private int rear;  // Index where the next element will be added
    private int size;  // Current number of elements
    private int capacity; // Maximum capacity of the queue

    // Constructor to initialize the queue with a given capacity
    @SuppressWarnings("unchecked")
    public ArrayQueue(int capacity) {
        this.capacity = capacity;
        this.queue = (T[]) new Object[capacity];
        this.front = 0;
        this.rear = 0;
        this.size = 0;
    }

    // Add an element to the rear of the queue
    public void enqueue(T data) {
        if (isFull()) {
            throw new IllegalStateException("Queue is full");
        }
        queue[rear] = data;
        rear = (rear + 1) % capacity; // Circular increment
        size++;
    }

    // Remove and return the front element of the queue
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        T data = queue[front];
        queue[front] = null; // Help with garbage collection
        front = (front + 1) % capacity; // Circular increment
        size--;
        return data;
    }

    // Get the front element without removing it
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return queue[front];
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Check if the queue is full
    public boolean isFull() {
        return size == capacity;
    }

    // Get the current size of the queue
    public int size() {
        return size;
    }

    // Print the queue
    public void print() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }
        System.out.print("Queue: ");
        int count = 0;
        int index = front;
        while (count < size) {
            System.out.print(queue[index] + " ");
            index = (index + 1) % capacity;
            count++;
        }
        System.out.println();
    }

    // Example usage
    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<>(5);

        // Add elements
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.print(); // Queue: 1 2 3

        // Remove and peek
        System.out.println("Dequeued: " + queue.dequeue()); // Dequeued: 1
        queue.print(); // Queue: 2 3
        System.out.println("Peek: " + queue.peek()); // Peek: 2

        // Add more elements
        queue.enqueue(4);
        queue.enqueue(5);
        queue.print(); // Queue: 2 3 4 5

        // Size and empty check
        System.out.println("Size: " + queue.size()); // Size: 4
        System.out.println("Is empty? " + queue.isEmpty()); // Is empty? false
        System.out.println("Is full? " + queue.isFull()); // Is full? false

        // Add to full queue
        queue.enqueue(6);
        queue.print(); // Queue: 2 3 4 5 6
    }
}