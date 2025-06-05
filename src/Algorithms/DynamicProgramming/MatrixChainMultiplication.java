package Algorithms.DynamicProgramming;

public class MatrixChainMultiplication {
    // Compute the minimum number of scalar multiplications needed for matrix chain multiplication
    public static int matrixChainOrder(int[] dims) {
        int n = dims.length - 1; // Number of matrices
        if (n <= 1) {
            return 0; // No multiplication needed for 0 or 1 matrix
        }

        // dp[i][j] represents the minimum cost to multiply matrices from i to j
        int[][] dp = new int[n][n];

        // Initialize dp table with 0 for single matrices (no cost)
        for (int i = 0; i < n; i++) {
            dp[i][i] = 0;
        }

        // l is the chain length (number of matrices in the subchain)
        for (int l = 2; l <= n; l++) {
            // i is the starting matrix index
            for (int i = 0; i < n - l + 1; i++) {
                int j = i + l - 1; // j is the ending matrix index
                dp[i][j] = Integer.MAX_VALUE; // Initialize to infinity

                // Try each possible position to split the chain
                for (int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k + 1][j] + dims[i] * dims[k + 1] * dims[j + 1];
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }
        return dp[0][n - 1]; // Minimum cost for the entire chain
    }

    // Compute the minimum cost and print the optimal parenthesization
    public static int matrixChainOrderWithParenthesis(int[] dims) {
        int n = dims.length - 1; // Number of matrices
        if (n <= 1) {
            return 0;
        }

        // dp[i][j] stores the minimum cost
        int[][] dp = new int[n][n];
        // bracket[i][j] stores the optimal split point for parenthesization
        int[][] bracket = new int[n][n];

        // Initialize dp table
        for (int i = 0; i < n; i++) {
            dp[i][i] = 0;
        }

        // l is the chain length
        for (int l = 2; l <= n; l++) {
            for (int i = 0; i < n - l + 1; i++) {
                int j = i + l - 1;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k + 1][j] + dims[i] * dims[k + 1] * dims[j + 1];
                    if (cost < dp[i][j]) {
                        dp[i][j] = cost;
                        bracket[i][j] = k;
                    }
                }
            }
        }

        // Print the optimal parenthesization
        System.out.println("Optimal Parenthesization: ");
        printParenthesis(bracket, 0, n - 1);
        System.out.println();
        return dp[0][n - 1];
    }

    // Helper method to print the optimal parenthesization
    private static void printParenthesis(int[][] bracket, int i, int j) {
        if (i == j) {
            System.out.print("A" + (i + 1));
            return;
        }
        System.out.print("(");
        printParenthesis(bracket, i, bracket[i][j]);
        printParenthesis(bracket, bracket[i][j] + 1, j);
        System.out.print(")");
    }

    // Example usage
    public static void main(String[] args) {
        // Array of dimensions: dims[i] is the number of rows of matrix i and columns of matrix i-1
        int[] dims = {10, 30, 5, 60}; // Matrices: A1(10x30), A2(30x5), A3(5x60)

        System.out.println("Matrix dimensions: " + java.util.Arrays.toString(dims));
        System.out.println("Minimum scalar multiplications (without parenthesis): " + matrixChainOrder(dims));

        System.out.println("\nMinimum scalar multiplications (with parenthesis): " +
                matrixChainOrderWithParenthesis(dims));

        // Another test case
        int[] dims2 = {40, 20, 30, 10, 30};
        System.out.println("\nMatrix dimensions: " + java.util.Arrays.toString(dims2));
        System.out.println("Minimum scalar multiplications (without parenthesis): " + matrixChainOrder(dims2));

        System.out.println("\nMinimum scalar multiplications (with parenthesis): " +
                matrixChainOrderWithParenthesis(dims2));
    }
}