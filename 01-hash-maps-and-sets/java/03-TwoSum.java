/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - For each number, I'd look up its complement in a value-to-index map.
 * - I'd check before inserting the current number so an element cannot pair with itself.
 * Time complexity: O(n) expected for n values.
 * Space complexity: O(n) auxiliary map space.
 */

import java.util.HashMap;
import java.util.Map;

// O(n) expected time and O(n) space.
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> indexByValue = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            Integer otherIndex = indexByValue.get(complement);
            if (otherIndex != null) {
                return new int[] {otherIndex, i};
            }
            indexByValue.put(nums[i], i);
        }
        throw new IllegalArgumentException("No valid pair");
    }
}

