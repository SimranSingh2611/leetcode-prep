/*
 * Interview quick notes — primary solution:
 * - Equal normalized prefix remainders identify sums divisible by k.
 * - Count earlier matching remainders before incrementing their frequency.
 * Time complexity: O(n) expected.
 * Space complexity: O(min(n + 1, k)) for positive k.
 */

// 974. Subarray Sums Divisible by K
// https://leetcode.com/problems/subarray-sums-divisible-by-k/
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        int prefixSum = 0, div = 0, ctr = 0;

        Map<Integer, Integer> prefixSumModKFreqMap = new HashMap<>();

        // Empty prefix has remainder 0
        prefixSumModKFreqMap.put(0, 1);

        for (int num : nums) {
            prefixSum += num;

            // Normalize the remainder
            div = prefixSum % k;

            if (div < 0) {
                div += k;
            }

            // Count previous prefixes with same remainder
            ctr += prefixSumModKFreqMap.getOrDefault(div, 0);

            // Update frequency
            prefixSumModKFreqMap.put(div, prefixSumModKFreqMap.getOrDefault(div, 0) + 1);
        }

        return ctr;
    }
}
