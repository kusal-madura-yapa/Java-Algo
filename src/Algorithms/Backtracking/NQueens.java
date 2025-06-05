package Algorithms.Backtracking;

/**
 * A class to solve the N-Queens problem in Java.
 * The N-Queens problem involves placing N queens on an NxN chessboard such that
 * no two queens can attack each other (no two queens share the same row, column,
 * or diagonal). This implementation uses backtracking to find a solution.
 */
public class NQueens {
    // Size of the chessboard (N x N)
    private int n;
    // Board to represent queen placements: board[i][j] = 1 if a queen is placed, 0 otherwise
    private int[][] board;

    // Constructor to initialize the board for N queens
    public NQueens(int n) {
        this.n = n;
        this.board = new int[n][n];
    }

    // Check if a queen can be placed at board[row][col]
    private boolean isSafe(int row, int col) {
        // Check row on the left
        for (int i = 0; i < col; i++) {
            if (board[row][i] == 1) {
                return false;
            }
        }

        // Check upper diagonal on the left
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        // Check lower diagonal on the left
        for (int i = row, j = col; i < n && j >= 0; i++, j--) {
            if (board[i][j] == 1) {
                return false;
            }
        }

        return true;
    }

    // Recursive method to solve the N-Queens problem
    private boolean solveRec(int col) {
        // Base case: If all queens are placed, return true
        if (col >= n) {
            return true;
        }

        // Try placing a queen in each row of the current column
        for (int i = 0; i < n; i++) {
            if (isSafe(i, col)) {
                // Place the queen
                board[i][col] = 1;

                // Recurse to place queens in the next column
                if (solveRec(col + 1)) {
                    return true;
                }

                // If placing queen at (i, col) doesn't lead to a solution, backtrack
                board[i][col] = 0;
            }
        }
        return false;
    }

    // Method to solve the N-Queens problem and print the solution
    public boolean solve() {
        if (!solveRec(0)) {
            System.out.println("No solution exists for N = " + n);
            return false;
        }
        printBoard();
        return true;
    }

    // Print the board configuration
    private void printBoard() {
        System.out.println("Solution for " + n + "-Queens:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(board[i][j] == 1 ? "Q " : ". ");
            }
            System.out.println();
        }
    }

    // Main method to demonstrate the N-Queens solver
    public static void main(String[] args) {
        // Test with N = 4
        int n = 4;
        System.out.println("Solving N-Queens for N = " + n);
        NQueens queens = new NQueens(n);
        queens.solve();

        // Test with N = 3 (no solution exists)
        n = 3;
        System.out.println("\nSolving N-Queens for N = " + n);
        queens = new NQueens(n);
        queens.solve();

        // Test with N = 8
        n = 8;
        System.out.println("\nSolving N-Queens for N = " + n);
        queens = new NQueens(n);
        queens.solve();
    }
}
