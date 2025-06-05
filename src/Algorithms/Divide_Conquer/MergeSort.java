package Algorithms.Divide_Conquer;

/**
 * A class implementing the Merge Sort algorithm in Java.
 * Merge Sort is a stable, divide-and-conquer sorting algorithm with O(n log n) time complexity.
 * This implementation includes methods to sort an array and merge subarrays, with a main method for demonstration.
 */
public class MergeSort {
    // Main method to sort the array using Merge Sort
    public void mergeSort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return; // No sorting needed for null or single-element array
        }
        int[] temp = new int[arr.length]; // Temporary array for merging
        mergeSortHelper(arr, temp, 0, arr.length - 1);
    }

    // Recursive helper method to divide the array and sort subarrays
    private void mergeSortHelper(int[] arr, int[] temp, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2; // Avoid potential overflow
            // Sort left half
            mergeSortHelper(arr, temp, left, mid);
            // Sort right half
            mergeSortHelper(arr, temp, mid + 1, right);
            // Merge the sorted halves
            merge(arr, temp, left, mid, right);
        }
    }

    // Method to merge two sorted subarrays: arr[left..mid] and arr[mid+1..right]
    private void merge(int[] arr, int[] temp, int left, int mid, int right) {
        // Copy data to temporary array
        for (int i = left; i <= right; i++) {
            temp[i] = arr[i];
        }

        int i = left;      // Index for left subarray
        int j = mid + 1;   // Index for right subarray
        int k = left;      // Index for merged array

        // Merge the two subarrays back into arr
        while (i <= mid && j <= right) {
            if (temp[i] <= temp[j]) {
                arr[k] = temp[i];
                i++;
            } else {
                arr[k] = temp[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of left subarray, if any
        while (i <= mid) {
            arr[k] = temp[i];
            i++;
            k++;
        }
        // Note: No need to copy remaining elements of right subarray,
        // as they are already in place in arr
    }

    // Main method to demonstrate Merge Sort
    public static void main(String[] args) {
        MergeSort sorter = new MergeSort();

        // Test cases
        int[][] testCases = {
                {64, 34, 25, 12, 22, 11, 90},
                {5, 2, 8, 1, 9, 3},
                {},
                {42},
                {10, 10, 10, 5, 5}
        };

        System.out.println("Demonstrating Merge Sort:");
        for (int i = 0; i < testCases.length; i++) {
            int[] arr = testCases[i];
            System.out.println("Test Case " + (i + 1) + ": Original array = " + java.util.Arrays.toString(arr));
            sorter.mergeSort(arr);
            System.out.println("Sorted array = " + java.util.Arrays.toString(arr));
            System.out.println();
        }
    }
}
