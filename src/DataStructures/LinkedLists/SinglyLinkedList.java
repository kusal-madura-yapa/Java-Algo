package DataStructures.LinkedLists;

public class SinglyLinkedList<T> {
    // Node class for the singly linked list
    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<T> head; // Head of the list
    private int size;     // Size of the list

    // Constructor to initialize an empty singly linked list
    public SinglyLinkedList() {
        head = null;
        size = 0;
    }

    // Add a node to the end of the list
    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    // Add a node at a specific index
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node<T> newNode = new Node<>(data);
        if (index == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
        size++;
    }

    // Remove a node with the given data
    public boolean remove(T data) {
        if (head == null) {
            return false;
        }
        if (head.data.equals(data)) {
            head = head.next;
            size--;
            return true;
        }
        Node<T> current = head;
        while (current.next != null) {
            if (current.next.data.equals(data)) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        return false; // Data not found
    }

    // Get the size of the list
    public int size() {
        return size;
    }

    // Check if the list is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Print the singly linked list
    public void print() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        System.out.print("Singly Linked List: ");
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    // Example usage
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

        // Add elements
        list.add(1);
        list.add(2);
        list.add(3);
        list.addAtIndex(1, 4);
        list.print(); // Singly Linked List: 1 -> 4 -> 2 -> 3 -> null

        // Remove elements
        System.out.println("Remove 4: " + list.remove(4)); // Remove 4: true
        list.print(); // Singly Linked List: 1 -> 2 -> 3 -> null
        System.out.println("Remove 5: " + list.remove(5)); // Remove 5: false

        // Size and empty check
        System.out.println("Size: " + list.size()); // Size: 3
        System.out.println("Is empty? " + list.isEmpty()); // Is empty? false

        // Add at beginning
        list.addAtIndex(0, 0);
        list.print(); // Singly Linked List: 0 -> 1 -> 2 -> 3 -> null
    }
}