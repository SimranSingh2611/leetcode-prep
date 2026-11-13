/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd sort by start and merge into the last output range while the next range overlaps.
 * - A gap finalizes the previous range; taking the maximum end also handles nested intervals.
 * Time complexity: O(n log(n + 1)) for n intervals.
 * Space complexity: O(n) auxiliary outer-array copy/object-sort workspace, plus O(n) output.
 */

// 56. Merge Intervals
// https://leetcode.com/problems/merge-intervals/
// NeetCode 150: https://neetcode.io/problems/merge-intervals/question?list=neetcode150
// O(n log n) time; O(n) auxiliary space plus output. Preserves input and copies output rows.
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {

    public int[][] merge(int[][] intervals) {
        // Questions to interviewer:
        //   1. intervals sorted or not.
        //   2. [1,4][4,7] can be merged into [1,7] or not.

        ArrayList<int[]> ans = new ArrayList<>();
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        // always add the first interval
        ans.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int prevEnd = ans.get(ans.size() - 1)[1];
            int curStart = intervals[i][0];
             //comparing the values of prevEnd and curStart
            if (curStart <= prevEnd) {
                // do the merging
                ans.get(ans.size() - 1)[1] = Math.max(ans.get(ans.size() - 1)[1], intervals[i][1]);
            } else {
                // just add the interval
                ans.add(intervals[i]);
            }
        }
        int[][] res = new int[ans.size()][2];
        ans.toArray(res);
        return res;
    }
}
