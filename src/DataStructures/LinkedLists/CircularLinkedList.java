package DataStructures.LinkedLists;

public class CircularLinkedList<T> {
    // Node class for the circular linked list
    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node<T> head; // Head of the circular linked list
    private int size;     // Size of the list

    // Constructor to initialize an empty circular linked list
    public CircularLinkedList() {
        head = null;
        size = 0;
    }

    // Add a node to the end of the list
    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
            head.next = head; // Point to itself to make it circular
        } else {
            Node<T> current = head;
            while (current.next != head) {
                current = current.next;
            }
            current.next = newNode;
            newNode.next = head; // Maintain circularity
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
                head.next = head;
            } else {
                Node<T> current = head;
                while (current.next != head) {
                    current = current.next;
                }
                newNode.next = head;
                head = newNode;
                current.next = head;
            }
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
        Node<T> current = head;
        Node<T> prev = null;
        do {
            if (current.data.equals(data)) {
                if (prev == null) { // Removing head
                    if (size == 1) {
                        head = null;
                    } else {
                        Node<T> last = head;
                        while (last.next != head) {
                            last = last.next;
                        }
                        head = current.next;
                        last.next = head;
                    }
                } else {
                    prev.next = current.next;
                }
                size--;
                return true;
            }
            prev = current;
            current = current.next;
        } while (current != head);
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

    // Print the circular linked list
    public void print() {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }
        Node<T> current = head;
        System.out.print("Circular Linked List: ");
        do {
            System.out.print(current.data + " -> ");
            current = current.next;
        } while (current != head);
        System.out.println("(head)");
    }

    // Example usage
    public static void main(String[] args) {
        CircularLinkedList<Integer> list = new CircularLinkedList<>();

        // Add elements
        list.add(1);
        list.add(2);
        list.add(3);
        list.addAtIndex(1, 4);
        list.print(); // Circular Linked List: 1 -> 4 -> 2 -> 3 -> (head)

        // Remove elements
        System.out.println("Remove 4: " + list.remove(4));
        list.print(); // Circular Linked List: 1 -> 2 -> 3 -> (head)
        System.out.println("Remove 5: " + list.remove(5)); // Remove 5: false

        // Size and empty check
        System.out.println("Size: " + list.size()); // Size: 3
        System.out.println("Is empty? " + list.isEmpty()); // Is empty? false

        // Add more elements and test
        list.addAtIndex(0, 0);
        list.print(); // Circular Linked List: 0 -> 1 -> 2 -> 3 -> (head)
    }
}