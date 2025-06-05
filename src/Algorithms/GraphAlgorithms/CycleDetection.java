package Algorithms.GraphAlgorithms;

public class CycleDetection {
    // Node class for the linked list
    private static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Detect a cycle in a linked list using Floyd's Cycle-Finding Algorithm (Tortoise and Hare)
    public static boolean hasCycle(Node head) {
        if (head == null || head.next == null) {
            return false;
        }

        Node slow = head; // Tortoise
        Node fast = head; // Hare

        // Move slow by one and fast by two steps
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true; // Cycle detected
            }
        }
        return false; // No cycle
    }

    // Find the starting point of the cycle in a linked list
    public static Node findCycleStart(Node head) {
        if (head == null || head.next == null) {
            return null;
        }

        Node slow = head;
        Node fast = head;
        boolean hasCycle = false;

        // Detect cycle using Floyd's algorithm
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                hasCycle = true;
                break;
            }
        }

        if (!hasCycle) {
            return null; // No cycle
        }

        // Find the cycle start
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow; // Starting point of the cycle
    }

    // Print the linked list (stops if a cycle is detected)
    public static void printList(Node head) {
        if (head == null) {
            System.out.println("List is empty");
            return;
        }

        System.out.print("Linked List: ");
        Node current = head;
        Node fast = head;
        boolean cycleDetected = false;

        while (current != null && !cycleDetected) {
            System.out.print(current.data + " -> ");
            current = current.next;
            if (fast != null && fast.next != null) {
                fast = fast.next.next;
                if (current == fast) {
                    cycleDetected = true;
                    System.out.print("(cycle detected at " + current.data + ")");
                }
            }
        }
        if (!cycleDetected) {
            System.out.println("null");
        } else {
            System.out.println();
        }
    }

    // Example usage
    public static void main(String[] args) {
        // Create a linked list: 1 -> 2 -> 3 -> 4 -> 5
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);

        // Test without cycle
        System.out.println("List without cycle:");
        printList(head);
        System.out.println("Has cycle? " + hasCycle(head));
        System.out.println("Cycle start: " + (findCycleStart(head) == null ? "null" : findCycleStart(head).data));

        // Create a cycle: 5 -> 3
        head.next.next.next.next.next = head.next.next;

        // Test with cycle
        System.out.println("\nList with cycle (5 -> 3):");
        printList(head);
        System.out.println("Has cycle? " + hasCycle(head));
        Node cycleStart = findCycleStart(head);
        System.out.println("Cycle start: " + (cycleStart == null ? "null" : cycleStart.data));
    }
}
