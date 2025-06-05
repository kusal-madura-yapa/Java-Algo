package Algorithms.Divide_Conquer;

public class BinarySearch {
    // Iterative binary search to find the index of a target in a sorted array
    public static int binarySearchIterative(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2; // Avoid potential overflow
            if (arr[mid] == target) {
                return mid; // Target found
            } else if (arr[mid] < target) {
                left = mid + 1; // Search right half
            } else {
                right = mid - 1; // Search left half
            }
        }
        return -1; // Target not found
    }

    // Recursive binary search to find the index of a target in a sorted array
    public static int binarySearchRecursive(int[] arr, int target) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        return binarySearchRec(arr, target, 0, arr.length - 1);
    }

    // Helper method for recursive binary search
    private static int binarySearchRec(int[] arr, int target, int left, int right) {
        if (left > right) {
            return -1; // Target not found
        }
        int mid = left + (right - left) / 2; // Avoid potential overflow
        if (arr[mid] == target) {
            return mid; // Target found
        } else if (arr[mid] < target) {
            return binarySearchRec(arr, target, mid + 1, right); // Search right half
        } else {
            return binarySearchRec(arr, target, left, mid - 1); // Search left half
        }
    }

    // Example usage
    public static void main(String[] args) {
        int[] arr = {2, 3, 4, 10, 40, 50, 60, 70};

        // Test iterative binary search
        int target1 = 10;
        int result1 = binarySearchIterative(arr, target1);
        System.out.println("Array: " + java.util.Arrays.toString(arr));
        System.out.println("Iterative search for " + target1 + ": Index " + result1);

        // Test recursive binary search
        int target2 = 50;
        int result2 = binarySearchRecursive(arr, target2);
        System.out.println("Recursive search for " + target2 + ": Index " + result2);

        // Test case for element not in array
        int target3 = 5;
        int result3 = binarySearchIterative(arr, target3);
        System.out.println("Iterative search for " + target3 + ": Index " + result3);

        // Test edge cases
        int[] empty = {};
        System.out.println("Search in empty array: " + binarySearchIterative(empty, 1)); // -1
        System.out.println("Search in null array: " + binarySearchIterative(null, 1)); // -1
    }
}
