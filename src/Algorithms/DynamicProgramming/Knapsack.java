package Algorithms.DynamicProgramming;

/**
 * A class to solve the 0/1 Knapsack problem in Java.
 * The 0/1 Knapsack problem involves selecting a subset of items with weights and values
 * to maximize total value without exceeding a given weight capacity.
 * This implementation uses dynamic programming for efficiency and includes examples.
 */
public class Knapsack {
    // Method to solve the 0/1 Knapsack problem using dynamic programming
    public int knapsack(int[] weights, int[] values, int capacity) {
        if (weights == null || values == null || weights.length != values.length || capacity < 0) {
            throw new IllegalArgumentException("Invalid input: weights, values, or capacity");
        }

        int n = weights.length;
        // Create a DP table: dp[i][w] represents max value for first i items and capacity w
        int[][] dp = new int[n + 1][capacity + 1];

        // Fill the DP table
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0; // Base case: no items or no capacity
                } else if (weights[i - 1] <= w) {
                    // Choose max of including or excluding the current item
                    dp[i][w] = Math.max(
                            values[i - 1] + dp[i - 1][w - weights[i - 1]], // Include item
                            dp[i - 1][w]                                     // Exclude item
                    );
                } else {
                    dp[i][w] = dp[i - 1][w]; // Cannot include item, weight too large
                }
            }
        }

        return dp[n][capacity];
    }

    // Method to find and print items included in the optimal solution
    public void printSelectedItems(int[] weights, int[] values, int capacity) {
        if (weights == null || values == null || weights.length != values.length || capacity < 0) {
            throw new IllegalArgumentException("Invalid input: weights, values, or capacity");
        }

        int n = weights.length;
        int[][] dp = new int[n + 1][capacity + 1];

        // Fill the DP table
        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                } else if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(
                            values[i - 1] + dp[i - 1][w - weights[i - 1]],
                            dp[i - 1][w]
                    );
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // Backtrack to find selected items
        System.out.print("Selected items (weight, value): ");
        int w = capacity;
        for (int i = n; i > 0 && w > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                System.out.print("(" + weights[i - 1] + ", " + values[i - 1] + ") ");
                w -= weights[i - 1];
            }
        }
        System.out.println();
    }

    // Main method to demonstrate the 0/1 Knapsack solver
    public static void main(String[] args) {
        Knapsack solver = new Knapsack();

        // Test cases
        int[][] weights = {
                {2, 3, 4, 5},
                {1, 2, 3},
                {}
        };
        int[][] values = {
                {3, 4, 5, 6},
                {2, 4, 6},
                {}
        };
        int[] capacities = {5, 4, 3};

        System.out.println("Demonstrating 0/1 Knapsack Problem:");
        for (int i = 0; i < weights.length; i++) {
            System.out.println("Test Case " + (i + 1) + ":");
            System.out.println("Weights: " + java.util.Arrays.toString(weights[i]));
            System.out.println("Values: " + java.util.Arrays.toString(values[i]));
            System.out.println("Capacity: " + capacities[i]);
            try {
                int maxValue = solver.knapsack(weights[i], values[i], capacities[i]);
                System.out.println("Maximum value: " + maxValue);
                solver.printSelectedItems(weights[i], values[i], capacities[i]);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println();
        }
    }
}