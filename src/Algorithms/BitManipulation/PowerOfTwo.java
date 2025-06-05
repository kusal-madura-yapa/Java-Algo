package Algorithms.BitManipulation;

/**
 * A class to determine if a given number is a power of two.
 * Provides multiple methods to check this property: iterative, recursive, and bit manipulation.
 * Includes a main method with examples for demonstration and understanding.
 */
public class PowerOfTwo {
    // Iterative method to check if a number is a power of 2
    public boolean isPowerOfTwoIterative(int n) {
        // Handle non-positive numbers
        if (n <= 0) {
            return false;
        }
        // Keep dividing by 2 until we reach 1
        while (n > 1) {
            if (n % 2 != 0) {
                return false;
            }
            n = n / 2;
        }
        return true;
    }

    // Recursive method to check if a number is a power of 2
    public boolean isPowerOfTwoRecursive(int n) {
        // Base cases: non-positive numbers are not powers of 2
        if (n <= 0) {
            return false;
        }
        // If n is 1, it's a power of 2 (2^0)
        if (n == 1) {
            return true;
        }
        // If n is odd, it's not a power of 2
        if (n % 2 != 0) {
            return false;
        }
        // Recursively check n/2
        return isPowerOfTwoRecursive(n / 2);
    }

    // Bit manipulation method to check if a number is a power of 2
    public boolean isPowerOfTwoBitwise(int n) {
        // Non-positive numbers are not powers of 2
        if (n <= 0) {
            return false;
        }
        // A power of 2 has exactly one bit set to 1
        // n & (n-1) clears the least significant set bit; if n is a power of 2, result is 0
        return (n & (n - 1)) == 0;
    }

    // Main method to demonstrate the power of two checks
    public static void main(String[] args) {
        PowerOfTwo checker = new PowerOfTwo();
        int[] testCases = {1, 2, 3, 4, 8, 16, 0, -4, 10, 32};

        System.out.println("Testing numbers for power of 2:");
        for (int num : testCases) {
            System.out.println("Number: " + num);
            System.out.println("Iterative method: " + checker.isPowerOfTwoIterative(num));
            System.out.println("Recursive method: " + checker.isPowerOfTwoRecursive(num));
            System.out.println("Bitwise method: " + checker.isPowerOfTwoBitwise(num));
            System.out.println();
        }
    }
}