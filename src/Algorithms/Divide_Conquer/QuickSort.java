package Algorithms.Divide_Conquer;

public class QuickSort {
    // QuickSort function to sort an array
    public static void quickSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        quickSortRec(arr, 0, arr.length - 1);
    }

    // Recursive helper method for QuickSort
    private static void quickSortRec(int[] arr, int low, int high) {
        if (low < high) {
            // Find the partition index
            int pi = partition(arr, low, high);

            // Recursively sort elements before and after partition
            quickSortRec(arr, low, pi - 1);
            quickSortRec(arr, pi + 1, high);
        }
    }

    // Partition function to rearrange array and return pivot index
    private static int partition(int[] arr, int low, int high) {
        // Choose the rightmost element as pivot
        int pivot = arr[high];
        int i = low - 1; // Index of smaller element

        // Traverse through all elements
        for (int j = low; j < high; j++) {
            // If current element is smaller than or equal to pivot
            if (arr[j] <= pivot) {
                i++; // Increment index of smaller element
                // Swap elements
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Place pivot in its correct position
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1; // Return pivot index
    }

    // Example usage
    public static void main(String[] args) {
        // Test case 1: Regular array
        int[] arr1 = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Original array: " + java.util.Arrays.toString(arr1));
        quickSort(arr1);
        System.out.println("Sorted array: " + java.util.Arrays.toString(arr1));

        // Test case 2: Already sorted array
        int[] arr2 = {1, 2, 3, 4, 5};
        System.out.println("\nOriginal array: " + java.util.Arrays.toString(arr2));
        quickSort(arr2);
        System.out.println("Sorted array: " + java.util.Arrays.toString(arr2));

        // Test case 3: Array with duplicates
        int[] arr3 = {5, 2, 9, 5, 2, 3, 5};
        System.out.println("\nOriginal array: " + java.util.Arrays.toString(arr3));
        quickSort(arr3);
        System.out.println("Sorted array: " + java.util.Arrays.toString(arr3));

        // Test case 4: Empty and null array
        int[] arr4 = {};
        int[] arr5 = null;
        System.out.println("\nEmpty array test:");
        quickSort(arr4);
        System.out.println("Sorted empty array: " + java.util.Arrays.toString(arr4));
        System.out.println("Null array test:");
        quickSort(arr5);
        System.out.println("Sorted null array: " + java.util.Arrays.toString(arr5));
    }
}
