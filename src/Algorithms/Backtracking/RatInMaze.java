package Algorithms.Backtracking;

public class RatInMaze {
    // Solve the rat in a maze problem and print one feasible path
    public static boolean solveMaze(int[][] maze) {
        int N = maze.length;
        int[][] solution = new int[N][N];

        // Initialize solution matrix with zeros
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                solution[i][j] = 0;
            }
        }

        // Start solving from position (0, 0)
        if (solveMazeUtil(maze, 0, 0, solution)) {
            printSolution(solution);
            return true;
        } else {
            System.out.println("No solution exists");
            return false;
        }
    }

    // Utility method to solve the maze using backtracking
    private static boolean solveMazeUtil(int[][] maze, int x, int y, int[][] solution) {
        int N = maze.length;

        // Base case: if rat reaches the destination (bottom-right)
        if (x == N - 1 && y == N - 1 && maze[x][y] == 1) {
            solution[x][y] = 1;
            return true;
        }

        // Check if the current position is valid
        if (isSafe(maze, x, y)) {
            // Mark the current cell as part of the solution path
            solution[x][y] = 1;

            // Move right
            if (solveMazeUtil(maze, x, y + 1, solution)) {
                return true;
            }

            // Move down
            if (solveMazeUtil(maze, x + 1, y, solution)) {
                return true;
            }

            // If neither right nor down works, backtrack
            solution[x][y] = 0;
            return false;
        }
        return false;
    }

    // Check if the position (x, y) is valid
    private static boolean isSafe(int[][] maze, int x, int y) {
        int N = maze.length;
        // Check if within bounds and cell is not blocked (1 means path, 0 means blocked)
        return (x >= 0 && x < N && y >= 0 && y < N && maze[x][y] == 1);
    }

    // Print the solution matrix
    private static void printSolution(int[][] solution) {
        System.out.println("Solution Path:");
        for (int i = 0; i < solution.length; i++) {
            for (int j = 0; j < solution.length; j++) {
                System.out.print(solution[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Example usage
    public static void main(String[] args) {
        // Example maze: 1 represents a path, 0 represents a blocked cell
        int[][] maze = {
                {1, 0, 0, 0},
                {1, 1, 0, 1},
                {0, 1, 0, 0},
                {1, 1, 1, 1}
        };

        System.out.println("Maze:");
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze.length; j++) {
                System.out.print(maze[i][j] + " ");
            }
            System.out.println();
        }

        solveMaze(maze);
    }
}
