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
// O(n log n) time; O(n) auxiliary space plus output. Preserves input and copies output rows.
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int[][] merge(int[][] intervals) {
        int[][] sorted = intervals.clone();
        Arrays.sort(sorted, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> merged = new ArrayList<>();
        for (int[] interval : sorted) {
            if (merged.isEmpty() || interval[0] > merged.get(merged.size() - 1)[1]) {
                merged.add(interval.clone());
            } else {
                int[] previous = merged.get(merged.size() - 1);
                previous[1] = Math.max(previous[1], interval[1]);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }
}
