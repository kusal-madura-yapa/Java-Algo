package DataStructures.Queues;

public class DequeCustom<T> {
    // Node class for the doubly linked list used in deque
    private static class Node<T> {
        T data;
        Node<T> next;
        Node<T> prev;

        Node(T data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    private Node<T> front; // Front of the deque
    private Node<T> rear;  // Rear of the deque
    private int size;      // Current size of the deque

    // Constructor to initialize an empty deque
    public DequeCustom() {
        front = null;
        rear = null;
        size = 0;
    }

    // Add an element to the front of the deque
    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            newNode.next = front;
            front.prev = newNode;
            front = newNode;
        }
        size++;
    }

    // Add an element to the rear of the deque
    public void addLast(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            newNode.prev = rear;
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    // Remove and return the element from the front of the deque
    public T removeFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }
        T data = front.data;
        front = front.next;
        size--;
        if (isEmpty()) {
            rear = null;
        } else {
            front.prev = null;
        }
        return data;
    }

    // Remove and return the element from the rear of the deque
    public T removeLast() {
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }
        T data = rear.data;
        rear = rear.prev;
        size--;
        if (isEmpty()) {
            front = null;
        } else {
            rear.next = null;
        }
        return data;
    }

    // Get the front element without removing it
    public T peekFirst() {
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }
        return front.data;
    }

    // Get the rear element without removing it
    public T peekLast() {
        if (isEmpty()) {
            throw new IllegalStateException("Deque is empty");
        }
        return rear.data;
    }

    // Check if the deque is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Get the current size of the deque
    public int size() {
        return size;
    }

    // Print the deque from front to rear
    public void print() {
        if (isEmpty()) {
            System.out.println("Deque is empty");
            return;
        }
        System.out.print("Deque (front to rear): ");
        Node<T> current = front;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    // Example usage
    public static void main(String[] args) {
        DequeCustom<Integer> deque = new DequeCustom<>();

        // Add elements
        deque.addFirst(1);
        deque.addLast(2);
        deque.addFirst(3);
        deque.addLast(4);
        deque.print(); // Deque (front to rear): 3 1 2 4

        // Peek operations
        System.out.println("Peek first: " + deque.peekFirst()); // Peek first: 3
        System.out.println("Peek last: " + deque.peekLast());   // Peek last: 4

        // Remove operations
        System.out.println("Remove first: " + deque.removeFirst()); // Remove first: 3
        System.out.println("Remove last: " + deque.removeLast());   // Remove last: 4
        deque.print(); // Deque (front to rear): 1 2

        // Size and empty check
        System.out.println("Size: " + deque.size()); // Size: 2
        System.out.println("Is empty? " + deque.isEmpty()); // Is empty? false

        // Remove remaining elements
        System.out.println("Remove first: " + deque.removeFirst()); // Remove first: 1
        System.out.println("Remove last: " + deque.removeLast());   // Remove last: 2
        deque.print(); // Deque is empty
    }
}
