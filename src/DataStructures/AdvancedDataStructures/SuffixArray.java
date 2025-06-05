package DataStructures.AdvancedDataStructures;

public class SuffixArray {
    // Class to hold suffix and its original index
    private static class Suffix implements Comparable<Suffix> {
        int index;
        String suffix;

        Suffix(int index, String suffix) {
            this.index = index;
            this.suffix = suffix;
        }

        @Override
        public int compareTo(Suffix other) {
            return this.suffix.compareTo(other.suffix);
        }
    }

    // Array to store the suffix array indices
    private int[] suffixArray;
    // The input string for which suffix array is built
    private String text;

    // Constructor to build the suffix array for the given string
    public SuffixArray(String text) {
        this.text = text;
        this.suffixArray = buildSuffixArray(text);
    }

    // Build the suffix array using a simple sorting approach
    private int[] buildSuffixArray(String text) {
        int n = text.length();
        // Create array of suffix objects
        Suffix[] suffixes = new Suffix[n];

        // Generate all suffixes and store with their starting indices
        for (int i = 0; i < n; i++) {
            suffixes[i] = new Suffix(i, text.substring(i));
        }

        // Sort the suffixes lexicographically
        java.util.Arrays.sort(suffixes);

        // Extract the indices into the suffix array
        int[] suffixArray = new int[n];
        for (int i = 0; i < n; i++) {
            suffixArray[i] = suffixes[i].index;
        }
        return suffixArray;
    }

    // Get the suffix array
    public int[] getSuffixArray() {
        return suffixArray;
    }

    // Search for a pattern in the text using the suffix array
    public int[] search(String pattern) {
        int n = text.length();
        int m = pattern.length();
        int[] result = new int[n];
        int resultCount = 0;

        // Binary search to find the range of suffixes that start with the pattern
        int left = binarySearchLeft(pattern);
        if (left == -1) {
            return new int[0]; // Pattern not found
        }

        // Find all occurrences within the range
        for (int i = left; i < n && text.substring(suffixArray[i]).startsWith(pattern); i++) {
            result[resultCount++] = suffixArray[i];
        }

        // Trim the result array to the actual number of matches
        return java.util.Arrays.copyOf(result, resultCount);
    }

    // Binary search to find the leftmost suffix that is >= pattern
    private int binarySearchLeft(String pattern) {
        int left = 0, right = suffixArray.length - 1;
        int result = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = text.substring(suffixArray[mid]).compareTo(pattern);

            if (comparison >= 0) {
                result = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return result;
    }

    // Example usage
    public static void main(String[] args) {
        // Example string
        String text = "banana";
        System.out.println("Text: " + text);

        // Create suffix array
        SuffixArray sa = new SuffixArray(text);
        int[] suffixArray = sa.getSuffixArray();

        // Print the suffix array
        System.out.println("Suffix Array indices: " + java.util.Arrays.toString(suffixArray));
        System.out.println("Suffixes in sorted order:");
        for (int i = 0; i < suffixArray.length; i++) {
            System.out.println("Index " + suffixArray[i] + ": " + text.substring(suffixArray[i]));
        }

        // Search for patterns
        String pattern = "ana";
        System.out.println("\nSearching for pattern: " + pattern);
        int[] matches = sa.search(pattern);
        System.out.println("Matches found at indices: " + java.util.Arrays.toString(matches));
        for (int idx : matches) {
            System.out.println("Match at index " + idx + ": " + text.substring(idx, idx + pattern.length()));
        }
    }
}
