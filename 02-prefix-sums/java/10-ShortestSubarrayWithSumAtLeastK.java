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

