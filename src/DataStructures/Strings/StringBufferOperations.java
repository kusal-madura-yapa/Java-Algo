package DataStructures.Strings;

public class StringBufferOperations {
    private StringBuffer buffer;

    // Constructor to initialize the StringBuffer
    public StringBufferOperations() {
        buffer = new StringBuffer();
    }

    // Constructor to initialize the StringBuffer with a string
    public StringBufferOperations(String initial) {
        buffer = new StringBuffer(initial);
    }

    // Append a string to the buffer
    public void append(String str) {
        buffer.append(str);
    }

    // Insert a string at a specific index
    public void insert(int index, String str) {
        if (index < 0 || index > buffer.length()) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + buffer.length());
        }
        buffer.insert(index, str);
    }

    // Delete a substring from start to end index
    public void delete(int start, int end) {
        if (start < 0 || end > buffer.length() || start > end) {
            throw new IndexOutOfBoundsException("Start: " + start + ", End: " + end + ", Length: " + buffer.length());
        }
        buffer.delete(start, end);
    }

    // Replace a substring from start to end index with a new string
    public void replace(int start, int end, String str) {
        if (start < 0 || end > buffer.length() || start > end) {
            throw new IndexOutOfBoundsException("Start: " + start + ", End: " + end + ", Length: " + buffer.length());
        }
        buffer.replace(start, end, str);
    }

    // Reverse the contents of the buffer
    public void reverse() {
        buffer.reverse();
    }

    // Get the current capacity of the buffer
    public int capacity() {
        return buffer.capacity();
    }

    // Get the current length of the buffer
    public int length() {
        return buffer.length();
    }

    // Get the string representation of the buffer
    public String toString() {
        return buffer.toString();
    }

    // Example usage
    public static void main(String[] args) {
        // Initialize with a string
        StringBufferOperations sbo = new StringBufferOperations("Hello");
        System.out.println("Initial string: " + sbo.toString()); // Initial string: Hello

        // Append operation
        sbo.append(" World");
        System.out.println("After append: " + sbo.toString()); // After append: Hello World

        // Insert operation
        sbo.insert(5, ",");
        System.out.println("After insert: " + sbo.toString()); // After insert: Hello, World

        // Replace operation
        sbo.replace(6, 11, "Everyone");
        System.out.println("After replace: " + sbo.toString()); // After replace: Hello, Everyone

        // Delete operation
        sbo.delete(5, 6);
        System.out.println("After delete: " + sbo.toString()); // After delete: Hello Everyone

        // Reverse operation
        sbo.reverse();
        System.out.println("After reverse: " + sbo.toString()); // After reverse: enoyrevE olleH

        // Capacity and length
        System.out.println("Capacity: " + sbo.capacity()); // Capacity: (default 16 + initial length, may vary)
        System.out.println("Length: " + sbo.length()); // Length: 13
    }
}