package DataStructures.Strings;

/**
 * A class demonstrating various operations of the StringBuilder class in Java.
 * StringBuilder is mutable, efficient for string manipulation, and provides methods
 * like append, insert, delete, replace, and reverse. This class includes examples
 * for common operations with clear output to understand the functionality.
 */
public class StringBuilderOperations {
    // Method to demonstrate basic StringBuilder operations
    public void demonstrateOperations() {
        // Initialize a StringBuilder with an initial string
        StringBuilder sb = new StringBuilder("Hello");

        // 1. Append: Add text to the end
        sb.append(" World");
        System.out.println("After append: " + sb);

        // 2. Insert: Add text at a specific index
        sb.insert(5, ",");
        System.out.println("After insert: " + sb);

        // 3. Delete: Remove characters from startIndex to endIndex-1
        sb.delete(6, 11);
        System.out.println("After delete: " + sb);

        // 4. Replace: Replace characters from startIndex to endIndex-1 with new text
        sb.replace(0, 5, "Hi");
        System.out.println("After replace: " + sb);

        // 5. Reverse: Reverse the entire string
        sb.reverse();
        System.out.println("After reverse: " + sb);

        // 6. Capacity and Length: Check current capacity and length
        System.out.println("Length: " + sb.length());
        System.out.println("Capacity: " + sb.capacity());

        // 7. Set Length: Truncate or extend the length (extends with null characters)
        sb.setLength(3);
        System.out.println("After setLength(3): " + sb);
    }

    // Main method to run the demonstration
    public static void main(String[] args) {
        // Create an instance of StringBuilderOperations
        StringBuilderOperations ops = new StringBuilderOperations();

        // Run the demonstration
        System.out.println("Demonstrating StringBuilder Operations:");
        ops.demonstrateOperations();

        // Additional example: Building a sentence dynamically
        StringBuilder sentence = new StringBuilder();
        sentence.append("Current time: ");
        sentence.append("07:17 PM +0530");
        sentence.append(" on Thursday, June 05, 2025");
        System.out.println("\nDynamic Sentence Example:");
        System.out.println("Built sentence: " + sentence);

        // Error handling example: Trying to access invalid index
        try {
            sentence.charAt(100);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}