package DataStructures.Strings;

/**
 * A class demonstrating various String operations in Java.
 * Strings are immutable, so operations create new String objects.
 * This class includes examples for concatenation, substring, searching,
 * case conversion, trimming, and splitting, with clear output for understanding.
 */
public class StringOperations {
    // Method to demonstrate basic String operations
    public void demonstrateOperations() {
        // Initialize a String
        String str = "  Hello, World!  ";

        // 1. Concatenation: Combine strings
        String concat = str + " Welcome";
        System.out.println("After concatenation: " + concat);

        // 2. Substring: Extract part of the string
        String sub = str.substring(2, 7);
        System.out.println("Substring (2, 7): " + sub);

        // 3. Searching: Check for content
        boolean contains = str.contains("World");
        System.out.println("Contains 'World': " + contains);
        int index = str.indexOf("o");
        System.out.println("Index of 'o': " + index);

        // 4. Case Conversion: Change to upper/lower case
        String upper = str.toUpperCase();
        System.out.println("To upper case: " + upper);
        String lower = str.toLowerCase();
        System.out.println("To lower case: " + lower);

        // 5. Trimming: Remove leading and trailing whitespace
        String trimmed = str.trim();
        System.out.println("After trim: '" + trimmed + "'");

        // 6. Length: Get the length of the string
        System.out.println("Length of original string: " + str.length());
        System.out.println("Length of trimmed string: " + trimmed.length());

        // 7. Splitting: Split string into an array
        String[] parts = trimmed.split(",");
        System.out.println("After split by ',':");
        for (String part : parts) {
            System.out.println("Part: '" + part + "'");
        }
    }

    // Main method to run the demonstration
    public static void main(String[] args) {
        // Create an instance of StringOperations
        StringOperations ops = new StringOperations();

        // Run the demonstration
        System.out.println("Demonstrating String Operations:");
        ops.demonstrateOperations();

        // Additional example: Building and manipulating a date string
        String date = "Thursday, June 05, 2025";
        System.out.println("\nDate String Example:");
        System.out.println("Original date: " + date);
        String replaced = date.replace("June", "Jul");
        System.out.println("After replacing 'June' with 'Jul': " + replaced);
        boolean startsWith = date.startsWith("Thu");
        System.out.println("Starts with 'Thu': " + startsWith);

        // Error handling example: Invalid substring index
        try {
            date.substring(100);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

