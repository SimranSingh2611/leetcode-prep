/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd keep candidate indices in a deque with decreasing values.
 * - Expired indices leave the front and dominated values leave the back, so the front is always
 *   the current maximum.
 * Time complexity: O(n) amortized overall.
 * Space complexity: O(k) auxiliary deque space, plus O(n - k + 1) output.
 */

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.TreeMap;

// Recommended: O(n) time and O(k) space.
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];
        Deque<Integer> decreasing = new ArrayDeque<>();

        for (int right = 0; right < nums.length; right++) {
            while (!decreasing.isEmpty() && decreasing.peekFirst() <= right - k) {
                decreasing.removeFirst();
            }
            while (!decreasing.isEmpty() && nums[decreasing.peekLast()] <= nums[right]) {
                decreasing.removeLast();
            }
            decreasing.addLast(right);

            if (right >= k - 1) {
                result[right - k + 1] = nums[decreasing.peekFirst()];
            }
        }
        return result;
    }
}

// Follow-up: simpler ordered multiset, O(n log k) time and O(k) space.
class TreeMapSolution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int[] result = new int[nums.length - k + 1];
        TreeMap<Integer, Integer> window = new TreeMap<>();

        for (int right = 0; right < nums.length; right++) {
            window.merge(nums[right], 1, Integer::sum);
            if (right >= k) {
                int leaving = nums[right - k];
                int remaining = window.get(leaving) - 1;
                if (remaining == 0) {
                    window.remove(leaving);
                } else {
                    window.put(leaving, remaining);
                }
            }
            if (right >= k - 1) {
                result[right - k + 1] = window.lastKey();
            }
        }
        return result;
    }
}

