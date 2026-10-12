import java.util.Arrays;

// O(n log n) time and O(1) auxiliary space, excluding sorting internals.
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
        int removals = 0;
        int previousEnd = Integer.MIN_VALUE;

        for (int[] interval : intervals) {
            if (interval[0] >= previousEnd) {
                previousEnd = interval[1];
            } else {
                removals++;
            }
        }
        return removals;
    }
}

