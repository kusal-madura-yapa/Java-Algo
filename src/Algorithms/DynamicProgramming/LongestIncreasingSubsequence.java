package Algorithms.DynamicProgramming;

public class LongestIncreasingSubsequence {
    // Find the length of the Longest Increasing Subsequence (LIS) using dynamic programming
    public static int findLISLength(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int n = arr.length;
        int[] dp = new int[n];
        int maxLength = 1; // Minimum LIS length is 1

        // Initialize dp array: each element starts as its own subsequence
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
        }

        // Compute LIS length for each position
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                }
            }
            maxLength = Math.max(maxLength, dp[i]);
        }
        return maxLength;
    }

    // Find the Longest Increasing Subsequence and return it as an array
    public static int[] findLIS(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }

        int n = arr.length;
        int[] dp = new int[n];
        int[] prev = new int[n]; // To track previous index for reconstruction
        int maxLength = 1;
        int maxIndex = 0;

        // Initialize dp and prev arrays
        for (int i = 0; i < n; i++) {
            dp[i] = 1;
            prev[i] = -1; // No previous element initially
        }

        // Compute LIS lengths and track previous indices
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    prev[i] = j;
                }
            }
            if (dp[i] > maxLength) {
                maxLength = dp[i];
                maxIndex = i;
            }
        }

        // Reconstruct the LIS
        int[] lis = new int[maxLength];
        int currentIndex = maxIndex;
        int lisIndex = maxLength - 1;
        while (currentIndex != -1) {
            lis[lisIndex] = arr[currentIndex];
            lisIndex--;
            currentIndex = prev[currentIndex];
        }
        return lis;
    }

    // Example usage
    public static void main(String[] args) {
        // Test case 1
        int[] arr1 = {10, 22, 9, 33, 21, 50, 41, 60, 80};
        System.out.println("Array: " + java.util.Arrays.toString(arr1));
        System.out.println("Length of LIS: " + findLISLength(arr1));
        System.out.println("LIS: " + java.util.Arrays.toString(findLIS(arr1)));

        // Test case 2
        int[] arr2 = {3, 10, 2, 1, 20};
        System.out.println("\nArray: " + java.util.Arrays.toString(arr2));
        System.out.println("Length of LIS: " + findLISLength(arr2));
        System.out.println("LIS: " + java.util.Arrays.toString(findLIS(arr2)));

        // Test case 3: Edge cases
        int[] arr3 = {};
        System.out.println("\nArray: " + java.util.Arrays.toString(arr3));
        System.out.println("Length of LIS: " + findLISLength(arr3));
        System.out.println("LIS: " + java.util.Arrays.toString(findLIS(arr3)));

        int[] arr4 = {5};
        System.out.println("\nArray: " + java.util.Arrays.toString(arr4));
        System.out.println("Length of LIS: " + findLISLength(arr4));
        System.out.println("LIS: " + java.util.Arrays.toString(findLIS(arr4)));
    }
}
