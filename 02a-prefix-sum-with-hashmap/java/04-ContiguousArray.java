/*
 * Interview quick notes — primary solution:
 * - Treat zero as -1 and one as +1; equal counts become a zero-sum range.
 * - Reuse LC 325's maximum-length helper with target zero after converting the input.
 * Input modification: replaces every zero in nums with -1.
 * Time complexity: O(n) expected.
 * Space complexity: O(n).
 */

// 525. Contiguous Array
// https://leetcode.com/problems/contiguous-array/
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int findMaxLength(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                nums[i] = -1;
            }
        }

        return maxSubArrayLen(nums, 0);
    }

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
