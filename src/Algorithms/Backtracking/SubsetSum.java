package Algorithms.Backtracking;

/**
 * A class to solve the Subset Sum problem in Java.
 * The Subset Sum problem determines if there exists a subset of a given set of integers
 * that sums to a target value. This implementation uses a dynamic programming approach
 * for efficiency and includes an example to demonstrate usage.
 */
public class SubsetSum {
    // Method to check if a subset with the given sum exists
    public boolean hasSubsetSum(int[] set, int target) {
        int n = set.length;
        // Create a DP table: dp[i][j] is true if a subset of elements from index 0 to i-1 sums to j
        boolean[][] dp = new boolean[n + 1][target + 1];

        // Empty subset sums to 0
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        // Fill the DP table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= target; j++) {
                if (j < set[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - set[i - 1]];
                }
            }
        }

        return dp[n][target];
    }

    // Method to find and print one possible subset that sums to the target
    public void printSubset(int[] set, int target) {
        int n = set.length;
        boolean[][] dp = new boolean[n + 1][target + 1];

        // Initialize base case: empty subset sums to 0
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }

        // Fill the DP table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= target; j++) {
                if (j < set[i - 1]) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - set[i - 1]];
                }
            }
        }

        // If no subset sums to target, print message and return
        if (!dp[n][target]) {
            System.out.println("No subset sums to " + target);
            return;
        }

        // Backtrack to find one valid subset
        System.out.print("Subset summing to " + target + ": ");
        int i = n;
        int j = target;
        while (i > 0 && j > 0) {
            if (dp[i][j] && !dp[i - 1][j]) {
                System.out.print(set[i - 1] + " ");
                j -= set[i - 1];
            }
            i--;
        }
        System.out.println();
    }

    // Main method to demonstrate the Subset Sum solver
    public static void main(String[] args) {
        SubsetSum solver = new SubsetSum();

        // Example 1
        int[] set1 = {3, 34, 4, 12, 5, 2};
        int target1 = 9;
        System.out.println("Set: {3, 34, 4, 12, 5, 2}");
        System.out.println("Target sum: " + target1);
        boolean exists1 = solver.hasSubsetSum(set1, target1);
        System.out.println("Subset exists: " + exists1);
        solver.printSubset(set1, target1);

        // Example 2
        int[] set2 = {1, 8, 2, 5};
        int target2 = 20;
        System.out.println("\nSet: {1, 8, 2, 5}");
        System.out.println("Target sum: " + target2);
        boolean exists2 = solver.hasSubsetSum(set2, target2);
        System.out.println("Subset exists: " + exists2);
        solver.printSubset(set2, target2);
    }
}
