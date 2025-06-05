package Algorithms.BitManipulation;

public class CountSetBits {
    // Count the number of 1 bits in an integer using basic bit manipulation
    public static int countSetBits(int num) {
        int count = 0;
        while (num != 0) {
            count += num & 1; // Add 1 if the least significant bit is 1
            num >>= 1;        // Right shift to check the next bit
        }
        return count;
    }

    // Count set bits using Brian Kernighan's algorithm
    public static int countSetBitsKernighan(int num) {
        int count = 0;
        while (num != 0) {
            num &= (num - 1); // Clear the least significant 1 bit
            count++;
        }
        return count;
    }

    // Count set bits in all numbers from 1 to n
    public static int[] countSetBitsRange(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("Input must be positive");
        }
        int[] result = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            result[i] = countSetBitsKernighan(i);
        }
        return result;
    }

    // Example usage
    public static void main(String[] args) {
        // Test individual number
        int num = 29; // Binary: 11101
        System.out.println("Number: " + num + " (Binary: " + Integer.toBinaryString(num) + ")");
        System.out.println("Set bits (basic method): " + countSetBits(num));        // 4
        System.out.println("Set bits (Kernighan's method): " + countSetBitsKernighan(num)); // 4

        // Test range of numbers
        int range = 5;
        int[] bitsInRange = countSetBitsRange(range);
        System.out.println("\nSet bits for numbers 1 to " + range + ":");
        for (int i = 1; i <= range; i++) {
            System.out.println("Number " + i + " (Binary: " + Integer.toBinaryString(i) + "): " + bitsInRange[i]);
        }
        // Expected output:
        // Number 1 (Binary: 1): 1
        // Number 2 (Binary: 10): 1
        // Number 3 (Binary: 11): 2
        // Number 4 (Binary: 100): 1
        // Number 5 (Binary: 101): 2
    }
}
