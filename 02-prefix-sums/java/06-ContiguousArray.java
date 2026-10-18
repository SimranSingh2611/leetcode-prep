/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd treat zeros as minus one so equal zeros and ones become a zero-sum range.
 * - Repeated running balances identify valid ranges; keeping the earliest index gives the
 *   longest one.
 * Time complexity: O(n) expected for n values.
 * Space complexity: O(n) auxiliary earliest-index map space.
 */

// 525. Contiguous Array
// https://leetcode.com/problems/contiguous-array/
import java.util.HashMap;
import java.util.Map;

// O(n) time and O(n) space.
class Solution {
    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> firstIndex = new HashMap<>();
        firstIndex.put(0, -1);

        int balance = 0;
        int longest = 0;
        for (int i = 0; i < nums.length; i++) {
            balance += nums[i] == 1 ? 1 : -1;
            Integer earlier = firstIndex.get(balance);
            if (earlier != null) {
                longest = Math.max(longest, i - earlier);
            } else {
                firstIndex.put(balance, i);
            }
        }
        return longest;
    }
}

