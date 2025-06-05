package Algorithms.Divide_Conquer;

public class StrassenMatrixMultiplication {
    // Multiply two square matrices using Strassen's algorithm
    public static int[][] strassenMultiply(int[][] A, int[][] B) {
        int n = A.length;
        // Base case: 1x1 matrix
        if (n == 1) {
            int[][] result = new int[1][1];
            result[0][0] = A[0][0] * B[0][0];
            return result;
        }

        // Ensure matrix size is a power of 2 by padding if necessary
        int newSize = nextPowerOfTwo(n);
        int[][] A_padded = padMatrix(A, newSize);
        int[][] B_padded = padMatrix(B, newSize);

        // Compute the result using Strassen's algorithm
        int[][] result_padded = strassenMultiplyRec(A_padded, B_padded);

        // Trim the result back to original size
        return trimMatrix(result_padded, n);
    }

    // Recursive helper method for Strassen's algorithm
    private static int[][] strassenMultiplyRec(int[][] A, int[][] B) {
        int n = A.length;

        // Base case: 1x1 matrix
        if (n == 1) {
            int[][] result = new int[1][1];
            result[0][0] = A[0][0] * B[0][0];
            return result;
        }

        // Divide matrices into quadrants
        int half = n / 2;
        int[][] A11 = new int[half][half];
        int[][] A12 = new int[half][half];
        int[][] A21 = new int[half][half];
        int[][] A22 = new int[half][half];
        int[][] B11 = new int[half][half];
        int[][] B12 = new int[half][half];
        int[][] B21 = new int[half][half];
        int[][] B22 = new int[half][half];

        // Split matrices
        splitMatrix(A, A11, A12, A21, A22, half);
        splitMatrix(B, B11, B12, B21, B22, half);

        // Strassen's seven multiplications
        int[][] P1 = strassenMultiplyRec(addMatrices(A11, A22), addMatrices(B11, B22));
        int[][] P2 = strassenMultiplyRec(addMatrices(A21, A22), B11);
        int[][] P3 = strassenMultiplyRec(A11, subtractMatrices(B12, B22));
        int[][] P4 = strassenMultiplyRec(A22, subtractMatrices(B21, B11));
        int[][] P5 = strassenMultiplyRec(addMatrices(A11, A12), B22);
        int[][] P6 = strassenMultiplyRec(subtractMatrices(A21, A11), addMatrices(B11, B12));
        int[][] P7 = strassenMultiplyRec(subtractMatrices(A12, A22), addMatrices(B21, B22));

        // Compute quadrants of the result
        int[][] C11 = addMatrices(subtractMatrices(addMatrices(P1, P4), P5), P7);
        int[][] C12 = addMatrices(P3, P5);
        int[][] C21 = addMatrices(P2, P4);
        int[][] C22 = addMatrices(subtractMatrices(addMatrices(P1, P3), P2), P6);

        // Combine quadrants into result matrix
        int[][] result = new int[n][n];
        combineMatrices(result, C11, C12, C21, C22, half);

        return result;
    }

    // Add two matrices
    private static int[][] addMatrices(int[][] A, int[][] B) {
        int n = A.length;
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = A[i][j] + B[i][j];
            }
        }
        return result;
    }

    // Subtract two matrices
    private static int[][] subtractMatrices(int[][] A, int[][] B) {
        int n = A.length;
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = A[i][j] - B[i][j];
            }
        }
        return result;
    }

    // Split a matrix into four quadrants
    private static void splitMatrix(int[][] parent, int[][] A11, int[][] A12, int[][] A21, int[][] A22, int half) {
        for (int i = 0; i < half; i++) {
            for (int j = 0; j < half; j++) {
                A11[i][j] = parent[i][j];
                A12[i][j] = parent[i][j + half];
                A21[i][j] = parent[i + half][j];
                A22[i][j] = parent[i + half][j + half];
            }
        }
    }

    // Combine four quadrants into a single matrix
    private static void combineMatrices(int[][] result, int[][] C11, int[][] C12, int[][] C21, int[][] C22, int half) {
        for (int i = 0; i < half; i++) {
            for (int j = 0; j < half; j++) {
                result[i][j] = C11[i][j];
                result[i][j + half] = C12[i][j];
                result[i + half][j] = C21[i][j];
                result[i + half][j + half] = C22[i][j];
            }
        }
    }

    // Pad a matrix to the next power of 2 size
    private static int[][] padMatrix(int[][] matrix, int newSize) {
        int n = matrix.length;
        int[][] padded = new int[newSize][newSize];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                padded[i][j] = matrix[i][j];
            }
        }
        // Remaining elements are already 0 due to array initialization
        return padded;
    }

    // Trim a matrix to the original size
    private static int[][] trimMatrix(int[][] matrix, int originalSize) {
        int[][] trimmed = new int[originalSize][originalSize];
        for (int i = 0; i < originalSize; i++) {
            for (int j = 0; j < originalSize; j++) {
                trimmed[i][j] = matrix[i][j];
            }
        }
        return trimmed;
    }

    // Find the next power of 2 for a given number
    private static int nextPowerOfTwo(int n) {
        int power = 1;
        while (power < n) {
            power *= 2;
        }
        return power;
    }

    // Print a matrix
    private static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    // Example usage
    public static void main(String[] args) {
        // Example matrices (must be square)
        int[][] A = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[][] B = {
                {9, 8, 7},
                {6, 5, 4},
                {3, 2, 1}
        };

        System.out.println("Matrix A:");
        printMatrix(A);
        System.out.println("\nMatrix B:");
        printMatrix(B);

        // Multiply matrices using Strassen's algorithm
        int[][] result = strassenMultiply(A, B);

        System.out.println("\nResult of A * B:");
        printMatrix(result);
        // Expected result for 3x3 matrices:
        // 30  24  18
        // 84  69  54
        // 138 114 90
    }
}
