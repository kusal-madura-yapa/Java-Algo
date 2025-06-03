package DataStructures.Arrays;
/**
 * ArrayOperations class provides methods to perform common array operations such as
 * initialization, traversal, searching, sorting, merging, and resizing.
 * This class is designed for educational purposes with clear comments to help understand
 * array manipulation in Java.
 */
public class ArrayOperations {

    /**
     * Initializes an array with the given size and fills it with default values (0).
     * @param size The size of the array to initialize.
     * @return An initialized integer array.
     */
    public int[] initializeArray(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Array size must be positive");
        }
        int[] array = new int[size];
        // Default values are 0 in Java, but you can modify to initialize with other values
        return array;
    }

    /**
     * Traverses and prints all elements in the array.
     * @param array The array to traverse.
     */
    public void traverseArray(int[] array) {
        if (array == null || array.length == 0) {
            System.out.println("Array is empty or null");
            return;
        }
        System.out.print("Array elements: ");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    /**
     * Performs linear search to find the index of a target element.
     * @param array The array to search in.
     * @param target The element to find.
     * @return Index of the target if found, else -1.
     */
    public int linearSearch(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }
        for (int i = 0; i < array.length; i++) {
            if (array[i] == target) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Sorts the array using Bubble Sort algorithm.
     * @param array The array to sort (in ascending order).
     */
    public void bubbleSort(int[] array) {
        if (array == null || array.length <= 1) {
            return;
        }
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            // Last i elements are already in place
            for (int j = 0; j < n - i - 1; j++) {
                // Swap if the element is greater than the next
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    /**
     * Merges two sorted arrays into a single sorted array.
     * @param array1 First sorted array.
     * @param array2 Second sorted array.
     * @return A new sorted array containing elements of both input arrays.
     */
    public int[] mergeSortedArrays(int[] array1, int[] array2) {
        if (array1 == null || array2 == null) {
            throw new IllegalArgumentException("Input arrays cannot be null");
        }
        int[] mergedArray = new int[array1.length + array2.length];
        int i = 0; // Index for array1
        int j = 0; // Index for array2
        int k = 0; // Index for mergedArray

        // Compare and merge elements in sorted order
        while (i < array1.length && j < array2.length) {
            if (array1[i] <= array2[j]) {
                mergedArray[k++] = array1[i++];
            } else {
                mergedArray[k++] = array2[j++];
            }
        }

        // Copy remaining elements from array1, if any
        while (i < array1.length) {
            mergedArray[k++] = array1[i++];
        }

        // Copy remaining elements from array2, if any
        while (j < array2.length) {
            mergedArray[k++] = array2[j++];
        }

        return mergedArray;
    }

    /**
     * Resizes an array to a new size, copying existing elements.
     * If new size is smaller, truncates elements; if larger, pads with 0s.
     * @param array The array to resize.
     * @param newSize The new size of the array.
     * @return A new array with the specified size.
     */
    public int[] resizeArray(int[] array, int newSize) {
        if (newSize < 0) {
            throw new IllegalArgumentException("New size must be non-negative");
        }
        int[] newArray = new int[newSize];
        if (array == null) {
            return newArray;
        }
        // Copy elements up to the minimum of old and new size
        int copyLength = Math.min(array.length, newSize);
        for (int i = 0; i < copyLength; i++) {
            newArray[i] = array[i];
        }
        return newArray;
    }

    /**
     * Main method with example usage of ArrayOperations methods.
     * This demonstrates how to use the class and can be used for testing.
     */
    public static void main(String[] args) {
        ArrayOperations ops = new ArrayOperations();

        // Initialize an array
        int[] array = ops.initializeArray(5);
        array[0] = 5; array[1] = 2; array[2] = 8; array[3] = 1; array[4] = 9;
        System.out.println("Initial array:");
        ops.traverseArray(array);

        // Perform linear search
        int target = 8;
        int index = ops.linearSearch(array, target);
        System.out.println("Index of " + target + ": " + index);

        // Sort the array
        ops.bubbleSort(array);
        System.out.println("Sorted array:");
        ops.traverseArray(array);

        // Merge two sorted arrays
        int[] array1 = {1, 3, 5};
        int[] array2 = {2, 4, 6};
        int[] merged = ops.mergeSortedArrays(array1, array2);
        System.out.println("Merged sorted array:");
        ops.traverseArray(merged);

        // Resize the array
        int[] resized = ops.resizeArray(array, 7);
        System.out.println("Resized array (new size 7):");
        ops.traverseArray(resized);
    }
}
