/*
 * Interview quick notes — primary solution:
 * - Removing ends leaves a contiguous middle with sum total - x.
 * - Keep the longest such middle and return n - longest, reducing to LC 325.
 * Time complexity: O(n) expected.
 * Space complexity: O(n).
 */

// 1658. Minimum Operations to Reduce X to Zero
// https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int minOperations(int[] nums, int x) {
        long total = 0;
        for (int num : nums) {
            total += num;
        }

        long target = total - x;

        // The problem's values are strictly positive.
        if (target < 0) {
            return -1;
        }
        if (target == 0) {
            return nums.length;
        }

        int longest = maxSubArrayLen(nums, target);
        return longest == 0 ? -1 : nums.length - longest;
    }

    private int maxSubArrayLen(int[] nums, long k) {
        Map<Long, Integer> firstIndexMap = new HashMap<>();
        firstIndexMap.put(0L, -1);

        long prefixSum = 0;
        int longest = 0;

        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];

            Integer earlierIndex = firstIndexMap.get(prefixSum - k);
            if (earlierIndex != null) {
                longest = Math.max(longest, i - earlierIndex);
            }

            firstIndexMap.putIfAbsent(prefixSum, i);
        }

        return longest;
    }
}
