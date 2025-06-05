package DataStructures.Stacks;

public class LinkedListStack<T> {
    // Node class for the linked list
    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<T> top; // Top of the stack
    private int size;    // Current size of the stack

    // Constructor to initialize an empty stack
    public LinkedListStack() {
        top = null;
        size = 0;
    }

    // Push an element onto the stack
    public void push(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.next = top;
        top = newNode;
        size++;
    }

    // Pop and return the top element of the stack
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        T data = top.data;
        top = top.next;
        size--;
        return data;
    }

    // Get the top element without removing it
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return top.data;
    }

    // Check if the stack is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Get the current size of the stack
    public int size() {
        return size;
    }

    // Print the stack
    public void print() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return;
        }
        System.out.print("Stack (top to bottom): ");
        Node<T> current = top;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    // Example usage
    public static void main(String[] args) {
        LinkedListStack<Integer> stack = new LinkedListStack<>();

        // Push elements
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.print(); // Stack (top to bottom): 3 2 1

        // Peek and pop
        System.out.println("Peek: " + stack.peek()); // Peek: 3
        System.out.println("Popped: " + stack.pop()); // Popped: 3
        stack.print(); // Stack (top to bottom): 2 1

        // Push more elements
        stack.push(4);
        stack.push(5);
        stack.print(); // Stack (top to bottom): 5 4 2 1

        // Size and empty check
        System.out.println("Size: " + stack.size()); // Size: 4
        System.out.println("Is empty? " + stack.isEmpty()); // Is empty? false

        // Pop remaining elements
        System.out.println("Popped: " + stack.pop()); // Popped: 5
        System.out.println("Popped: " + stack.pop()); // Popped: 4
        stack.print(); // Stack (top to bottom): 2 1
    }
}
