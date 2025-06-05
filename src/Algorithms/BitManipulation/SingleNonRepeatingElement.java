package Algorithms.BitManipulation;

/**
 * A class to find the single non-repeating element in an array where every other
 * element appears exactly twice. Provides two approaches: one using a hash map
 * for general understanding and another using bitwise XOR for efficiency.
 * Includes a main method with examples for demonstration.
 */
public class SingleNonRepeatingElement {
    // Method using HashMap to find the single non-repeating element
    public int findSingleNonRepeatingWithHashMap(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array is null or empty");
        }

        java.util.HashMap<Integer, Integer> frequency = new java.util.HashMap<>();

        // Count frequency of each element
        for (int num : arr) {
            frequency.put(num, frequency.getOrDefault(num, 0) + 1);
        }

        // Find the element with frequency 1
        for (int num : arr) {
            if (frequency.get(num) == 1) {
                return num;
            }
        }

        throw new IllegalStateException("No non-repeating element found");
    }

    // Method using XOR to find the single non-repeating element
    public int findSingleNonRepeatingWithXOR(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array is null or empty");
        }

        int result = 0;
        // XOR all elements; pairs cancel out (a XOR a = 0), leaving the single element
        for (int num : arr) {
            result ^= num;
        }

        if (result == 0) {
            throw new IllegalStateException("No non-repeating element found or result is ambiguous");
        }

        return result;
    }

    // Main method to demonstrate both approaches
    public static void main(String[] args) {
        SingleNonRepeatingElement solver = new SingleNonRepeatingElement();

        // Test cases
        int[][] testCases = {
                {7, 3, 5, 4, 5, 3, 4},  // 7 is non-repeating
                {1, 2, 1, 3, 2},       // 3 is non-repeating
                {1, 1, 2, 2, 3}        // 3 is non-repeating
        };

        System.out.println("Testing Single Non-Repeating Element Finder:");
        for (int i = 0; i < testCases.length; i++) {
            System.out.println("Test Case " + (i + 1) + ": Array = " + java.util.Arrays.toString(testCases[i]));
            try {
                int resultHashMap = solver.findSingleNonRepeatingWithHashMap(testCases[i]);
                System.out.println("HashMap Method: Single non-repeating element = " + resultHashMap);
            } catch (Exception e) {
                System.out.println("HashMap Method: " + e.getMessage());
            }
            try {
                int resultXOR = solver.findSingleNonRepeatingWithXOR(testCases[i]);
                System.out.println("XOR Method: Single non-repeating element = " + resultXOR);
            } catch (Exception e) {
                System.out.println("XOR Method: " + e.getMessage());
            }
            System.out.println();
        }

        // Error case: empty array
        try {
            int[] empty = {};
            solver.findSingleNonRepeatingWithHashMap(empty);
        } catch (Exception e) {
            System.out.println("Error Case (Empty Array): " + e.getMessage());
        }
    }
}
