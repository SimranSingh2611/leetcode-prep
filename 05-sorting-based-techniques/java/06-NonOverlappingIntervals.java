/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd keep compatible intervals in earliest-finish order.
 * - An earlier finish leaves at least as much room for future intervals, so counting rejected
 *   ranges minimizes removals.
 * Time complexity: O(n log(n + 1)) for n intervals.
 * Space complexity: O(n) worst-case Java object-sort workspace; the greedy scan uses O(1)
 *   state.
 */

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

