/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd copy the prefix before the new interval, merge its overlap block, then copy the suffix.
 * - The existing sorted disjoint ranges make this one pass; touching closed endpoints must
 *   merge.
 * Time complexity: O(n) for n existing intervals.
 * Space complexity: O(n) result/buffer storage, with O(1) scan state beyond it.
 */

// 57. Insert Interval
// https://leetcode.com/problems/insert-interval/
// NeetCode 150: https://neetcode.io/problems/insert-new-interval/question?list=neetcode150
// Solution does not mutate either input and copies emitted intervals to avoid output/input
// aliasing.
import java.util.*;

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {

        List<int[]> out = new ArrayList<>();
        int i=0;
        int len = intervals.length;

        // add all the intervals ending before newInterval starts i.e. newInterval starts before current one end -> no overlap -> simply add. 
        while(i < len && newInterval[0] > intervals[i][1]) {
            out.add(intervals[i]);
            i++;
        }

        // merge all overlapping intervals to one considering newInterval
        // if end of new interval >= start of current one -> overlap -> can be merged.
        while(i < len && newInterval[1] >= intervals[i][0]) {
            // we could mutate newInterval here also.
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }

        // add the union of intervals we got
        out.add(newInterval);

        // add all the rest
        while(i < len) {
            out.add(intervals[i]);
            i++;
        }

        return out.toArray(new int[out.size()][]);
    }
}
