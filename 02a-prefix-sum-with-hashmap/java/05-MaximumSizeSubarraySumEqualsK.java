/*
 * Interview quick notes — primary solution:
 * - Look up prefixSum - k, just as in LC 560, but store indices instead of counts.
 * - Preserve each prefix's earliest index to maximize the length.
 * Time complexity: O(n) expected.
 * Space complexity: O(n).
 */

// 325. Maximum Size Subarray Sum Equals k
// https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
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
