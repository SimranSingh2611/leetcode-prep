/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd sort by end and keep each interval compatible with the last one retained.
 * - Earliest finish leaves the most room for later choices; touching endpoints are compatible
 *   for this question.
 * Time complexity: O(n log(n + 1)) for n intervals.
 * Space complexity: O(n) auxiliary cloned-array/object-sort storage; the scan uses O(1) state.
 */

// 435. Non-overlapping Intervals
// https://leetcode.com/problems/non-overlapping-intervals/
// O(n log n) time; O(n) auxiliary space for the copy/sort. Preserves input.
import java.util.Arrays;

class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        int[][] sorted = intervals.clone();
        Arrays.sort(sorted, (a, b) -> Integer.compare(a[1], b[1]));
        int removals = 0;
        int previousEnd = Integer.MIN_VALUE;
        for (int[] interval : sorted) {
            if (interval[0] >= previousEnd) {
                previousEnd = interval[1];
            } else {
                removals++;
            }
        }
        return removals;
    }
}
