package DataStructures.LinkedLists;

public class DoublyLinkedList<T> {
    // Node class for the doubly linked list
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

    private Node<T> head; // Head of the list
    private Node<T> tail; // Tail of the list
    private int size;     // Size of the list

    // Constructor to initialize an empty doubly linked list
    public DoublyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    // Add a node to the end of the list
    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
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
            if (head == null) {
                head = newNode;
                tail = newNode;
            } else {
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
            }
        } else if (index == size) {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        } else {
            Node<T> current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            newNode.next = current;
            newNode.prev = current.prev;
            current.prev.next = newNode;
            current.prev = newNode;
        }
        size++;
    }

    // Remove a node with the given data
    public boolean remove(T data) {
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(data)) {
                if (current == head) {
                    head = current.next;
                    if (head != null) {
                        head.prev = null;
                    } else {
                        tail = null;
                    }
                } else if (current == tail) {
                    tail = current.prev;
                    tail.next = null;
                } else {
                    current.prev.next = current.next;
                    current.next.prev = current.prev;
                }
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

    // Print the list from head to tail
    public void printForward() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        System.out.print("Doubly Linked List (forward): ");
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.data + " <-> ");
            current = current.next;
        }
        System.out.println("null");
    }

    // Print the list from tail to head
    public void printBackward() {
        if (tail == null) {
            System.out.println("List is empty");
            return;
        }
        System.out.print("Doubly Linked List (backward): ");
        Node<T> current = tail;
        while (current != null) {
            System.out.print(current.data + " <-> ");
            current = current.prev;
        }
        System.out.println("null");
    }

    // Example usage
    public static void main(String[] args) {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

        // Add elements
        list.add(1);
        list.add(2);
        list.add(3);
        list.addAtIndex(1, 4);
        list.printForward();  // Doubly Linked List (forward): 1 <-> 4 <-> 2 <-> 3 <-> null
        list.printBackward(); // Doubly Linked List (backward): 3 <-> 2 <-> 4 <-> 1 <-> null

        // Remove elements
        System.out.println("Remove 4: " + list.remove(4)); // Remove 4: true
        list.printForward();  // Doubly Linked List (forward): 1 <-> 2 <-> 3 <-> null
        list.printBackward(); // Doubly Linked List (backward): 3 <-> 2 <-> 1 <-> null
        System.out.println("Remove 5: " + list.remove(5)); // Remove 5: false

        // Size and empty check
        System.out.println("Size: " + list.size()); // Size: 3
        System.out.println("Is empty? " + list.isEmpty()); // Is empty? false

        // Add at beginning
        list.addAtIndex(0, 0);
        list.printForward();  // Doubly Linked List (forward): 0 <-> 1 <-> 2 <-> 3 <-> null
    }
}

