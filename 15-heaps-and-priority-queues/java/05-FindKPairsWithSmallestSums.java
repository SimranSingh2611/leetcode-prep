/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd treat each first-array value as the start of a sorted row of pair sums.
 * - A min-heap merges those rows by emitting a pair and advancing only its row; only the first
 *   min(k, n) rows need seeds.
 * Time complexity: O((h + p) log(h + 1)), where h = min(k, n), p = min(k, nm), and array
 *   lengths are n, m.
 * Space complexity: O(h) auxiliary heap, plus O(p) output pairs.
 */

// 373. Find K Pairs with Smallest Sums
// https://leetcode.com/problems/find-k-pairs-with-smallest-sums/
// Solution uses long pair sums in comparisons and leaves both arrays unchanged.
import java.util.*;

class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> out = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0) {
            return out;
        }
        PriorityQueue<int[]> heap =
                new PriorityQueue<>(
                        (a, b) ->
                                Long.compare(
                                        (long) nums1[a[0]] + nums2[a[1]],
                                        (long) nums1[b[0]] + nums2[b[1]]));
        for (int i = 0; i < Math.min(k, nums1.length); i++) {
            heap.offer(new int[] {i, 0});
        }
        while (out.size() < k && !heap.isEmpty()) {
            int[] p = heap.poll();
            out.add(Arrays.asList(nums1[p[0]], nums2[p[1]]));
            if (p[1] + 1 < nums2.length) {
                heap.offer(new int[] {p[0], p[1] + 1});
            }
        }
        return out;
    }
}
