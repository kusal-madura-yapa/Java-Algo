package Algorithms.Backtracking;

/**
 * A class to solve a 9x9 Sudoku puzzle in Java.
 * Uses a backtracking algorithm to fill the grid such that each row, column,
 * and 3x3 subgrid contains all digits from 1 to 9 without repetition.
 * Includes methods to solve and print the board, with an example for demonstration.
 */
public class SudokuSolver {
    // Size of the Sudoku grid (9x9)
    private static final int GRID_SIZE = 9;

    // Method to check if a number can be placed at board[row][col]
    private boolean isSafe(int[][] board, int row, int col, int num) {
        // Check row
        for (int x = 0; x < GRID_SIZE; x++) {
            if (board[row][x] == num) {
                return false;
            }
        }

        // Check column
        for (int x = 0; x < GRID_SIZE; x++) {
            if (board[x][col] == num) {
                return false;
            }
        }

        // Check 3x3 subgrid
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[startRow + i][startCol + j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    // Recursive method to solve the Sudoku puzzle
    private boolean solveSudoku(int[][] board) {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                // Find an empty cell (represented by 0)
                if (board[row][col] == 0) {
                    // Try digits 1 to 9
                    for (int num = 1; num <= 9; num++) {
                        if (isSafe(board, row, col, num)) {
                            // Place the number if safe
                            board[row][col] = num;

                            // Recursively try to solve the rest
                            if (solveSudoku(board)) {
                                return true;
                            }

                            // Backtrack if no solution is found
                            board[row][col] = 0;
                        }
                    }
                    return false; // No number can be placed, backtrack
                }
            }
        }
        return true; // Puzzle is solved
    }

    // Public method to solve the Sudoku puzzle
    public boolean solve(int[][] board) {
        // Validate board dimensions
        if (board == null || board.length != GRID_SIZE || board[0].length != GRID_SIZE) {
            System.out.println("Invalid board dimensions!");
            return false;
        }

        if (solveSudoku(board)) {
            System.out.println("Sudoku puzzle solved successfully:");
            printBoard(board);
            return true;
        } else {
            System.out.println("No solution exists for the given Sudoku puzzle.");
            return false;
        }
    }

    // Method to print the Sudoku board
    private void printBoard(int[][] board) {
        for (int i = 0; i < GRID_SIZE; i++) {
            if (i % 3 == 0 && i != 0) {
                System.out.println("- - - - - - - - - - - -");
            }
            for (int j = 0; j < GRID_SIZE; j++) {
                if (j % 3 == 0 && j != 0) {
                    System.out.print("| ");
                }
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Main method to demonstrate the Sudoku solver
    public static void main(String[] args) {
        // Example Sudoku puzzle (0 represents empty cells)
        int[][] board = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };

        SudokuSolver solver = new SudokuSolver();
        System.out.println("Original Sudoku puzzle:");
        solver.printBoard(board);
        System.out.println("\nSolving the puzzle...");
        solver.solve(board);
    }
}