/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd keep candidate prefix indices in increasing prefix-sum order in a deque.
 * - The front produces valid shortest ranges, while a newer smaller prefix dominates an older
 *   larger one; this also handles negative values.
 * Time complexity: O(n) amortized overall; each index enters and leaves the deque at most once.
 * Space complexity: O(n) auxiliary prefix-array and deque space.
 */

import java.util.ArrayDeque;
import java.util.Deque;

// O(n) time and O(n) space using a monotonic deque of prefix indices.
class Solution {
    public int shortestSubarray(int[] nums, int k) {
        long[] prefix = new long[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        int shortest = nums.length + 1;
        Deque<Integer> candidates = new ArrayDeque<>();
        for (int i = 0; i < prefix.length; i++) {
            while (!candidates.isEmpty() && prefix[i] - prefix[candidates.peekFirst()] >= k) {
                shortest = Math.min(shortest, i - candidates.removeFirst());
            }
            while (!candidates.isEmpty() && prefix[i] <= prefix[candidates.peekLast()]) {
                candidates.removeLast();
            }
            candidates.addLast(i);
        }
        return shortest <= nums.length ? shortest : -1;
    }
}

