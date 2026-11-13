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
// NeetCode 150: https://neetcode.io/problems/non-overlapping-intervals/question?list=neetcode150
// O(n log n) time; O(n) auxiliary space for the copy/sort. Preserves input.
import java.util.Arrays;

class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 1)
            return 0;

        Arrays.sort(intervals, (a, b) -> (a[0] - b[0]));

        int ctr = 0;
        int prevEnd = intervals[0][1];
        for(int i=1; i<intervals.length; i++) {
            int currStart = intervals[i][0];
            int currEnd = intervals[i][1];

            if (currStart < prevEnd) {
                // intervals are overlapping.
                ctr++;
                prevEnd = Math.min(prevEnd, currEnd);
            } else {
                prevEnd = currEnd;
            }
        }
        return ctr;
    }
}
