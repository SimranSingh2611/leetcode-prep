/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd copy ranges before the new interval, merge its contiguous overlap block, then copy the
 *   suffix.
 * - Already sorted disjoint intervals make the scan linear, and touching closed endpoints count
 *   as overlap.
 * Time complexity: O(n) for n existing intervals.
 * Space complexity: O(1) scan state, plus O(n) result/buffer storage.
 */

// 57. Insert Interval
// https://leetcode.com/problems/insert-interval/
// Solution does not mutate either input and copies emitted intervals to avoid output/input
// aliasing.
import java.util.*;

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> out = new ArrayList<>();
        int i = 0;
        int start = newInterval[0], end = newInterval[1];
        while (i < intervals.length && intervals[i][1] < start) {
            out.add(intervals[i++].clone());
        }
        while (i < intervals.length && intervals[i][0] <= end) {
            start = Math.min(start, intervals[i][0]);
            end = Math.max(end, intervals[i][1]);
            i++;
        }
        out.add(new int[] {start, end});
        while (i < intervals.length) {
            out.add(intervals[i++].clone());
        }
        return out.toArray(new int[out.size()][]);
    }
}
