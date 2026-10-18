/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd count earlier prefix sums in a map seeded with zero once.
 * - For each current sum, the frequency of current - k counts all matching subarrays ending
 *   here; I'd query before inserting.
 * Time complexity: O(n) expected for n values.
 * Space complexity: O(n) auxiliary prefix-frequency space.
 */

// 560. Subarray Sum Equals K
// https://leetcode.com/problems/subarray-sum-equals-k/
import java.util.HashMap;
import java.util.Map;

// O(n) expected time and O(n) space.
class Solution {
    public int subarraySum(int[] nums, int k) {
        Map<Long, Integer> prefixFrequency = new HashMap<>();
        prefixFrequency.put(0L, 1);

        long prefix = 0;
        int count = 0;
        for (int value : nums) {
            prefix += value;
            count += prefixFrequency.getOrDefault(prefix - k, 0);
            prefixFrequency.merge(prefix, 1, Integer::sum);
        }
        return count;
    }
}

