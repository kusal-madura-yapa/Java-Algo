package Algorithms.BitManipulation;

public class BitManipulation {
    // Get the bit at a specific position
    public static int getBit(int num, int position) {
        if (position < 0 || position > 31) {
            throw new IllegalArgumentException("Position must be between 0 and 31");
        }
        return (num >> position) & 1;
    }

    // Set the bit at a specific position to 1
    public static int setBit(int num, int position) {
        if (position < 0 || position > 31) {
            throw new IllegalArgumentException("Position must be between 0 and 31");
        }
        return num | (1 << position);
    }

    // Clear the bit at a specific position to 0
    public static int clearBit(int num, int position) {
        if (position < 0 || position > 31) {
            throw new IllegalArgumentException("Position must be between 0 and 31");
        }
        return num & ~(1 << position);
    }

    // Toggle the bit at a specific position
    public static int toggleBit(int num, int position) {
        if (position < 0 || position > 31) {
            throw new IllegalArgumentException("Position must be between 0 and 31");
        }
        return num ^ (1 << position);
    }

    // Check if a number is even using bit manipulation
    public static boolean isEven(int num) {
        return (num & 1) == 0;
    }

    // Count the number of 1 bits in a number
    public static int countSetBits(int num) {
        int count = 0;
        while (num != 0) {
            count += num & 1;
            num >>= 1;
        }
        return count;
    }

    // Check if a number is a power of 2
    public static boolean isPowerOfTwo(int num) {
        if (num <= 0) {
            return false;
        }
        return (num & (num - 1)) == 0;
    }

    // Find the single number in an array where every other number appears twice
    public static int findSingleNumber(int[] arr) {
        int result = 0;
        for (int num : arr) {
            result ^= num;
        }
        return result;
    }

    // Example usage
    public static void main(String[] args) {
        int num = 29; // Binary: 11101

        // Get bit
        System.out.println("Number: " + num + " (Binary: " + Integer.toBinaryString(num) + ")");
        System.out.println("Bit at position 2: " + getBit(num, 2)); // 1

        // Set bit
        int setResult = setBit(num, 1);
        System.out.println("After setting bit at position 1: " + setResult + " (Binary: " + Integer.toBinaryString(setResult) + ")"); // 31 (11111)

        // Clear bit
        int clearResult = clearBit(num, 2);
        System.out.println("After clearing bit at position 2: " + clearResult + " (Binary: " + Integer.toBinaryString(clearResult) + ")"); // 25 (11001)

        // Toggle bit
        int toggleResult = toggleBit(num, 2);
        System.out.println("After toggling bit at position 2: " + toggleResult + " (Binary: " + Integer.toBinaryString(toggleResult) + ")"); // 25 (11001)

        // Check if even
        System.out.println("Is " + num + " even? " + isEven(num)); // false

        // Count set bits
        System.out.println("Number of 1 bits in " + num + ": " + countSetBits(num)); // 4

        // Check if power of 2
        int testNum = 16;
        System.out.println("Is " + testNum + " a power of 2? " + isPowerOfTwo(testNum)); // true
        System.out.println("Is " + num + " a power of 2? " + isPowerOfTwo(num)); // false

        // Find single number
        int[] arr = {2, 3, 5, 4, 5, 3, 4};
        System.out.println("Single number in array: " + findSingleNumber(arr)); // 2
    }
}