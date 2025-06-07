package Algorithms.GreedyAlgorithms;

import java.util.Arrays;
import java.util.Comparator;

/**
 * A Java implementation of the Activity Selection Problem using a greedy approach.
 * The goal is to select the maximum number of non-conflicting activities that can be
 * performed by a single person, given their start and finish times.
 *
 * Example:
 * Suppose you have activities with the following start and finish times:
 * Activities:  A1    A2    A3    A4    A5    A6
 * Start:       1     3     0     5     8     5
 * Finish:      4     5     6     7     9     9
 *
 * Sorted by finish time: A1(1,4), A2(3,5), A4(5,7), A5(8,9), A6(5,9), A3(0,6)
 * Selected activities: A1(1,4), A4(5,7), A5(8,9)
 * Output: Maximum non-conflicting activities: [A1, A4, A5]
 *
 * Approach:
 * 1. Sort activities by finish time.
 * 2. Select the first activity (earliest finish time).
 * 3. For each subsequent activity, if its start time is >= the last selected activity's finish time,
 *    include it in the solution.
 */
public class ActivitySelection {

    // Class to represent an activity with start and finish times
    static class Activity {
        int start;
        int finish;
        String name; // For easy identification in output (e.g., "A1", "A2", etc.)

        public Activity(int start, int finish, String name) {
            this.start = start;
            this.finish = finish;
            this.name = name;
        }

        @Override
        public String toString() {
            return name + "(" + start + "," + finish + ")";
        }
    }

    /**
     * Selects the maximum number of non-conflicting activities using a greedy approach.
     * @param activities Array of Activity objects
     * @return String representation of selected activities
     */
    public static String selectActivities(Activity[] activities) {
        // Edge case: if no activities or null, return empty result
        if (activities == null || activities.length == 0) {
            return "No activities to select.";
        }

        // Step 1: Sort activities by finish time
        Arrays.sort(activities, Comparator.comparingInt(a -> a.finish));

        // StringBuilder to store the result
        StringBuilder result = new StringBuilder();
        result.append("Maximum non-conflicting activities: [");

        // Step 2: Select the first activity (earliest finish time)
        Activity lastSelected = activities[0];
        result.append(lastSelected.name);

        // Step 3: Iterate through remaining activities
        for (int i = 1; i < activities.length; i++) {
            // If current activity's start time is >= last selected activity's finish time
            if (activities[i].start >= lastSelected.finish) {
                result.append(", ").append(activities[i].name);
                lastSelected = activities[i]; // Update last selected activity
            }
        }

        result.append("]");
        return result.toString();
    }

    /**
     * Main method with an example to demonstrate the Activity Selection Problem.
     */
    public static void main(String[] args) {
        // Example: Create an array of activities
        Activity[] activities = new Activity[] {
                new Activity(1, 4, "A1"),
                new Activity(3, 5, "A2"),
                new Activity(0, 6, "A3"),
                new Activity(5, 7, "A4"),
                new Activity(8, 9, "A5"),
                new Activity(5, 9, "A6")
        };

        // Print original activities
        System.out.println("Available activities:");
        for (Activity activity : activities) {
            System.out.println(activity);
        }

        // Select and print maximum non-conflicting activities
        System.out.println("\n" + selectActivities(activities));
    }
}