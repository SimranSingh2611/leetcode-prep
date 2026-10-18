/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd track the earliest index of each prefix remainder modulo k.
 * - Equal remainders identify a multiple of k; I'd also require the index gap to be at least
 *   two.
 * Time complexity: O(n) expected for n values.
 * Space complexity: O(min(n + 1, k)) auxiliary space for positive k; O(n) if extending the
 *   implementation to k = 0.
 */

// 523. Continuous Subarray Sum
// https://leetcode.com/problems/continuous-subarray-sum/
import java.util.HashMap;
import java.util.Map;

// O(n) time and O(min(n, k)) space for the problem's positive k constraint.
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Long, Integer> firstIndex = new HashMap<>();
        firstIndex.put(0L, -1);

        long prefix = 0;
        for (int i = 0; i < nums.length; i++) {
            prefix += nums[i];
            long remainder = prefix % k;
            Integer earlier = firstIndex.get(remainder);
            if (earlier != null) {
                if (i - earlier >= 2) {
                    return true;
                }
            } else {
                firstIndex.put(remainder, i);
            }
        }
        return false;
    }
}

