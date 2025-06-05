package DataStructures.Queues;

public class LinkedListQueue<T> {
    // Node class for the linked list
    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<T> front; // Front of the queue
    private Node<T> rear;  // Rear of the queue
    private int size;      // Current size of the queue

    // Constructor to initialize an empty queue
    public LinkedListQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    // Add an element to the rear of the queue
    public void enqueue(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    // Remove and return the front element of the queue
    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        T data = front.data;
        front = front.next;
        size--;
        if (isEmpty()) {
            rear = null;
        }
        return data;
    }

    // Get the front element without removing it
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        return front.data;
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return size == 0;
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
        Node<T> current = front;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    // Example usage
    public static void main(String[] args) {
        LinkedListQueue<Integer> queue = new LinkedListQueue<>();

        // Add elements
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.print(); // Queue: 1 2 3

        // Peek and dequeue
        System.out.println("Peek: " + queue.peek()); // Peek: 1
        System.out.println("Dequeued: " + queue.dequeue()); // Dequeued: 1
        queue.print(); // Queue: 2 3

        // Add more elements
        queue.enqueue(4);
        queue.enqueue(5);
        queue.print(); // Queue: 2 3 4 5

        // Size and empty check
        System.out.println("Size: " + queue.size()); // Size: 4
        System.out.println("Is empty? " + queue.isEmpty()); // Is empty? false

        // Remove remaining elements
        System.out.println("Dequeued: " + queue.dequeue()); // Dequeued: 2
        System.out.println("Dequeued: " + queue.dequeue()); // Dequeued: 3
        queue.print(); // Queue: 4 5
    }
}