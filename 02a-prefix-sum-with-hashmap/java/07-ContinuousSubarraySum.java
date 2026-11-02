/*
 * Interview quick notes — primary solution:
 * - Equal prefix remainders identify a subarray whose sum is a multiple of k.
 * - Keep the earliest index and require an index gap of at least two.
 * Time complexity: O(n) expected.
 * Space complexity: O(min(n + 1, k)) for the problem's positive k.
 */

// 523. Continuous Subarray Sum
// https://leetcode.com/problems/continuous-subarray-sum/
import java.util.HashMap;
import java.util.Map;

class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Long, Integer> firstIndexMap = new HashMap<>();
        firstIndexMap.put(0L, -1);

        long prefixSum = 0;

        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            long remainder = prefixSum % k;

            Integer earlierIndex = firstIndexMap.get(remainder);
            if (earlierIndex != null && i - earlierIndex >= 2) {
                return true;
            }

            firstIndexMap.putIfAbsent(remainder, i);
        }

        return false;
    }
}
