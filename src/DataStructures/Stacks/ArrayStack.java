package DataStructures.Stacks;

/**
 * A custom implementation of a Stack in Java using an array.
 * This stack supports basic operations like push, pop, peek, and checking if it's empty or full.
 * Follows the Last-In-First-Out (LIFO) principle.
 */
public class ArrayStack<T> {
    // Array to store the stack elements
    private T[] stack;
    // Index of the top element in the stack
    private int top;
    // Maximum capacity of the stack
    private int capacity;

    // Constructor to initialize the stack with a given capacity
    @SuppressWarnings("unchecked")
    public ArrayStack(int capacity) {
        this.capacity = capacity;
        this.stack = (T[]) new Object[capacity];
        this.top = -1; // Stack is initially empty, so top is -1
    }

    // Push an element onto the stack
    public void push(T element) {
        if (isFull()) {
            throw new IllegalStateException("Stack is full! Cannot push element: " + element);
        }
        top++;
        stack[top] = element;
    }

    // Pop and return the top element from the stack
    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty! Cannot pop element.");
        }
        T element = stack[top];
        stack[top] = null; // Help garbage collection
        top--;
        return element;
    }

    // Peek at the top element without removing it
    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty! Cannot peek.");
        }
        return stack[top];
    }

    // Check if the stack is empty
    public boolean isEmpty() {
        return top == -1;
    }

    // Check if the stack is full
    public boolean isFull() {
        return top == capacity - 1;
    }

    // Return the current size of the stack
    public int size() {
        return top + 1;
    }

    // Main method to demonstrate the usage of ArrayStack
    public static void main(String[] args) {
        // Create a stack with capacity 5
        ArrayStack<String> stack = new ArrayStack<>(5);

        // Push some elements
        System.out.println("Pushing elements to the stack:");
        stack.push("Task 1: Start project");
        System.out.println("Pushed: Task 1: Start project");
        stack.push("Task 2: Write code");
        System.out.println("Pushed: Task 2: Write code");
        stack.push("Task 3: Test code");
        System.out.println("Pushed: Task 3: Test code");

        // Print the size of the stack
        System.out.println("Stack size: " + stack.size());

        // Peek at the top element
        System.out.println("Top element (peek): " + stack.peek());

        // Pop elements in LIFO order
        System.out.println("\nPopping elements from the stack:");
        while (!stack.isEmpty()) {
            System.out.println("Popped: " + stack.pop());
        }

        // Try to pop from an empty stack (will throw exception)
        try {
            stack.pop();
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // Try to push to a full stack
        try {
            stack.push("Task 1");
            stack.push("Task 2");
            stack.push("Task 3");
            stack.push("Task 4");
            stack.push("Task 5");
            stack.push("Task 6"); // This will throw an exception
        } catch (IllegalStateException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}