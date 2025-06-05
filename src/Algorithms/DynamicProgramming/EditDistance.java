package Algorithms.DynamicProgramming;

/**
 * A class to compute the Edit Distance (Levenshtein Distance) between two strings.
 * The Edit Distance is the minimum number of single-character operations (insertions,
 * deletions, or substitutions) required to transform one string into another.
 * This implementation uses dynamic programming for efficiency and includes examples.
 */
public class EditDistance {
    // Method to compute the edit distance between two strings
    public int computeEditDistance(String str1, String str2) {
        if (str1 == null || str2 == null) {
            throw new IllegalArgumentException("Input strings cannot be null");
        }

        int m = str1.length();
        int n = str2.length();

        // Create a DP table of size (m+1) x (n+1)
        int[][] dp = new int[m + 1][n + 1];

        // Initialize first row and column
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i; // Cost of deleting characters from str1
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j; // Cost of inserting characters from str2
        }

        // Fill the DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]; // No operation needed
                } else {
                    dp[i][j] = 1 + Math.min(
                            Math.min(dp[i - 1][j],    // Deletion
                                    dp[i][j - 1]),   // Insertion
                            dp[i - 1][j - 1]  // Substitution
                    );
                }
            }
        }

        return dp[m][n];
    }

    // Main method to demonstrate edit distance computation
    public static void main(String[] args) {
        EditDistance solver = new EditDistance();

        // Test cases
        String[][] testCases = {
                {"sunday", "saturday"},
                {"cat", "cut"},
                {"kitten", "sitting"},
                {"", "test"},
                {"same", "same"}
        };

        System.out.println("Demonstrating Edit Distance Computation:");
        for (int i = 0; i < testCases.length; i++) {
            String str1 = testCases[i][0];
            String str2 = testCases[i][1];
            System.out.println("Test Case " + (i + 1) + ":");
            System.out.println("String 1: \"" + str1 + "\"");
            System.out.println("String 2: \"" + str2 + "\"");
            try {
                int distance = solver.computeEditDistance(str1, str2);
                System.out.println("Edit Distance: " + distance);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println();
        }

        // Error case: null input
        try {
            solver.computeEditDistance(null, "test");
        } catch (Exception e) {
            System.out.println("Error Case (Null Input): " + e.getMessage());
        }
    }
}
