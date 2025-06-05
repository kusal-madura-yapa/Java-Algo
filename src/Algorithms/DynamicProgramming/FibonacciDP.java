package Algorithms.DynamicProgramming;


public class FibonacciDP {
    // Iterative Dynamic Programming approach to compute nth Fibonacci number
    public static long fibIterativeDP(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Input must be non-negative");
        }
        if (n <= 1) {
            return n;
        }

        // Array to store Fibonacci numbers
        long[] dp = new long[n + 1];
        dp[0] = 0;
        dp[1] = 1;

        // Build solution iteratively
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    // Recursive Dynamic Programming (memoization) approach to compute nth Fibonacci number
    public static long fibRecursiveDP(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Input must be non-negative");
        }
        // Array for memoization, initialized with -1 to indicate uncomputed values
        long[] memo = new long[n + 1];
        for (int i = 0; i <= n; i++) {
            memo[i] = -1;
        }
        return fibRecursiveDPHelper(n, memo);
    }

    // Helper method for recursive DP
    private static long fibRecursiveDPHelper(int n, long[] memo) {
        if (n <= 1) {
            return n;
        }
        // If already computed, return memoized result
        if (memo[n] != -1) {
            return memo[n];
        }
        // Compute and store result
        memo[n] = fibRecursiveDPHelper(n - 1, memo) + fibRecursiveDPHelper(n - 2, memo);
        return memo[n];
    }

    // Example usage
    public static void main(String[] args) {
        int n = 10;

        // Test iterative DP
        System.out.println("Fibonacci number at position " + n + " (Iterative DP): " + fibIterativeDP(n));

        // Test recursive DP
        System.out.println("Fibonacci number at position " + n + " (Recursive DP): " + fibRecursiveDP(n));

        // Print first few Fibonacci numbers
        System.out.println("\nFirst " + (n + 1) + " Fibonacci numbers (Iterative DP):");
        for (int i = 0; i <= n; i++) {
            System.out.print(fibIterativeDP(i) + " ");
        }

        // Test edge cases
        System.out.println("\n\nEdge cases:");
        System.out.println("Fib(0): " + fibIterativeDP(0)); // 0
        System.out.println("Fib(1): " + fibIterativeDP(1)); // 1
    }
}
