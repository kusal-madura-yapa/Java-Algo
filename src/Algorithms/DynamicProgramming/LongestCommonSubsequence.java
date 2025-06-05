package Algorithms.DynamicProgramming;

/**
 * A class to compute the Longest Common Subsequence (LCS) between two strings.
 * The LCS is the longest sequence of characters that appears in the same order
 * (not necessarily contiguous) in both strings. This implementation uses dynamic
 * programming for efficiency and includes a method to retrieve the LCS string.
 */
public class LongestCommonSubsequence {
    // Method to compute the length of the Longest Common Subsequence
    public int lcsLength(String str1, String str2) {
        if (str1 == null || str2 == null) {
            throw new IllegalArgumentException("Input strings cannot be null");
        }

        int m = str1.length();
        int n = str2.length();
        // Create a DP table of size (m+1) x (n+1)
        int[][] dp = new int[m + 1][n + 1];

        // Fill the DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n];
    }

    // Method to compute and return the Longest Common Subsequence string
    public String lcsString(String str1, String str2) {
        if (str1 == null || str2 == null) {
            throw new IllegalArgumentException("Input strings cannot be null");
        }

        int m = str1.length();
        int n = str2.length();
        int[][] dp = new int[m + 1][n + 1];

        // Fill the DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        // Backtrack to construct the LCS string
        StringBuilder lcs = new StringBuilder();
        int i = m, j = n;
        while (i > 0 && j > 0) {
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                lcs.insert(0, str1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] >= dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        return lcs.toString();
    }

    // Main method to demonstrate LCS computation
    public static void main(String[] args) {
        LongestCommonSubsequence solver = new LongestCommonSubsequence();

        // Test cases
        String[][] testCases = {
                {"ABCDGH", "AEDFHR"},
                {"AGGTAB", "GXTXAYB"},
                {"", "TEST"},
                {"XYZ", "XYZ"},
                {"HELLO", "WORLD"}
        };

        System.out.println("Demonstrating Longest Common Subsequence:");
        for (int i = 0; i < testCases.length; i++) {
            String str1 = testCases[i][0];
            String str2 = testCases[i][1];
            System.out.println("Test Case " + (i + 1) + ":");
            System.out.println("String 1: \"" + str1 + "\"");
            System.out.println("String 2: \"" + str2 + "\"");
            try {{
                int length = solver.lcsLength(str1, str2);
                String lcs = solver.lcsString(str1, str2);
                System.out.println("LCS Length: " + length);
                System.out.println("LCS String: \"" + lcs + "\"");
            }} catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println();
        }

        // Error case: null input
        try {
            solver.lcsLength(null, "TEST");
        } catch (Exception e) {
            System.out.println("Error Case (Null Input): " + e.getMessage());
        }
    }
}
