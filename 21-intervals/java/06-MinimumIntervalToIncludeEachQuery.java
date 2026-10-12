// 1851. Minimum Interval to Include Each Query
// https://leetcode.com/problems/minimum-interval-to-include-each-query/
// Solution clones the interval outer array, never edits its rows, and preserves query order in the
// returned answers. Requires heaps from 15.
import java.util.*;

class Solution {
    public int[] minInterval(int[][] intervals, int[] queries) {
        int[][] sorted = intervals.clone();
        Arrays.sort(sorted, (a, b) -> Integer.compare(a[0], b[0]));
        Integer[] order = new Integer[queries.length];
        for (int i = 0; i < order.length; i++) {
            order[i] = i;
        }
        Arrays.sort(order, (a, b) -> Integer.compare(queries[a], queries[b]));
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        int[] out = new int[queries.length];
        int i = 0;
        for (int qi : order) {
            int query = queries[qi];
            while (i < sorted.length && sorted[i][0] <= query) {
                int[] interval = sorted[i++];
                heap.offer(new int[] {interval[1] - interval[0] + 1, interval[1]});
            }
            while (!heap.isEmpty() && heap.peek()[1] < query) {
                heap.poll();
            }
            out[qi] = heap.isEmpty() ? -1 : heap.peek()[0];
        }
        return out;
    }
}
