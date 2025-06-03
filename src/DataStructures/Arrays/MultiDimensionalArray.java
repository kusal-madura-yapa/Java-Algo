package DataStructures.Arrays;

/**
 * MultiDimensionalArray class provides methods to perform operations on multi-dimensional arrays,
 * such as initialization, traversal, searching, and manipulation. This class focuses on 2D arrays
 * for simplicity but can be extended to higher dimensions. It is designed for educational purposes
 * with detailed comments to help understand multi-dimensional array handling in Java.
 */
public class MultiDimensionalArray {

    /**
     * Initializes a 2D array with the given number of rows and columns, filled with default values (0).
     * @param rows The number of rows in the 2D array.
     * @param cols The number of columns in the 2D array.
     * @return A 2D integer array initialized with zeros.
     */
    public int[][] initialize2DArray(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("Rows and columns must be positive");
        }
        int[][] array = new int[rows][cols];
        // Default values are 0 in Java, but you can modify to initialize with other values
        return array;
    }

    /**
     * Traverses and prints all elements in a 2D array in row-major order.
     * @param array The 2D array to traverse.
     */
    public void traverse2DArray(int[][] array) {
        if (array == null || array.length == 0 || array[0].length == 0) {
            System.out.println("Array is empty or null");
            return;
        }
        System.out.println("2D Array elements (row-major):");
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println(); // New line after each row
        }
    }

    /**
     * Searches for a target element in a 2D array and returns its position.
     * Uses linear search, checking each element in row-major order.
     * @param array The 2D array to search in.
     * @param target The element to find.
     * @return An int array [row, col] if found, else [-1, -1].
     */
    public int[] search2DArray(int[][] array, int target) {
        if (array == null || array.length == 0 || array[0].length == 0) {
            return new int[]{-1, -1};
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    /**
     * Transposes a 2D array (swaps rows and columns).
     * Assumes the input is a rectangular matrix (all rows have same length).
     * @param array The 2D array to transpose.
     * @return A new 2D array with rows and columns swapped.
     */
    public int[][] transpose2DArray(int[][] array) {
        if (array == null || array.length == 0 || array[0].length == 0) {
            throw new IllegalArgumentException("Array is empty or null");
        }
        int rows = array.length;
        int cols = array[0].length;
        int[][] transposed = new int[cols][rows];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transposed[j][i] = array[i][j];
            }
        }
        return transposed;
    }

    /**
     * Rotates a square 2D array (matrix) 90 degrees clockwise.
     * Assumes the input is a square matrix (rows == cols).
     * @param array The 2D array to rotate.
     */
    public void rotate2DArray(int[][] array) {
        if (array == null || array.length == 0 || array[0].length == 0) {
            throw new IllegalArgumentException("Array is empty or null");
        }
        if (array.length != array[0].length) {
            throw new IllegalArgumentException("Array must be square");
        }
        int n = array.length;

        // Step 1: Transpose the matrix (swap elements across the main diagonal)
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = array[i][j];
                array[i][j] = array[j][i];
                array[j][i] = temp;
            }
        }

        // Step 2: Reverse each row
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = array[i][j];
                array[i][j] = array[i][n - 1 - j];
                array[i][n - 1 - j] = temp;
            }
        }
    }

    /**
     * Main method with example usage of MultiDimensionalArray methods.
     * This demonstrates how to use the class and can be used for testing.
     */
    public static void main(String[] args) {
        MultiDimensionalArray ops = new MultiDimensionalArray();

        // Initialize a 2D array
        int[][] array = ops.initialize2DArray(3, 4);
        // Fill with sample values
        int value = 1;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                array[i][j] = value++;
            }
        }
        System.out.println("Initial 2D array:");
        ops.traverse2DArray(array);

        // Search for an element
        int target = 6;
        int[] position = ops.search2DArray(array, target);
        System.out.println("Position of " + target + ": [" + position[0] + ", " + position[1] + "]");

        // Transpose the array
        int[][] transposed = ops.transpose2DArray(array);
        System.out.println("Transposed 2D array:");
        ops.traverse2DArray(transposed);

        // Rotate a square 2D array (create a new 3x3 array for rotation)
        int[][] squareArray = ops.initialize2DArray(3, 3);
        value = 1;
        for (int i = 0; i < squareArray.length; i++) {
            for (int j = 0; j < squareArray[i].length; j++) {
                squareArray[i][j] = value++;
            }
        }
        System.out.println("Original square 2D array:");
        ops.traverse2DArray(squareArray);
        ops.rotate2DArray(squareArray);
        System.out.println("Rotated 2D array (90 degrees clockwise):");
        ops.traverse2DArray(squareArray);
    }
}