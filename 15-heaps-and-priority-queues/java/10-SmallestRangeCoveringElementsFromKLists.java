// 632. Smallest Range Covering Elements from K Lists
// https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/
// Solution compares widths as long and breaks equal-width ties by smaller left endpoint; lists are
// unchanged.
import java.util.*;

class Solution {
    public int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.size(); i++) {
            int value = nums.get(i).get(0);
            heap.offer(new int[] {value, i, 0});
            max = Math.max(max, value);
        }
        int left = heap.peek()[0], right = max;
        while (true) {
            int[] p = heap.poll();
            int min = p[0];
            long width = (long) max - min, best = (long) right - left;
            if (width < best || (width == best && min < left)) {
                left = min;
                right = max;
            }
            int next = p[2] + 1;
            if (next == nums.get(p[1]).size()) {
                break;
            }
            int value = nums.get(p[1]).get(next);
            max = Math.max(max, value);
            heap.offer(new int[] {value, p[1], next});
        }
        return new int[] {left, right};
    }
}
