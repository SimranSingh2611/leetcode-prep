/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd scan once and keep a set of values already seen.
 * - A repeated value lets me return immediately; the set replaces checking every pair.
 * Time complexity: O(n) expected for n values.
 * Space complexity: O(n) auxiliary space for the set.
 */

// 217. Contains Duplicate
// https://leetcode.com/problems/contains-duplicate/
// NeetCode 150: https://neetcode.io/problems/duplicate-integer/question?list=neetcode150
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// Recommended: O(n) expected time, O(n) space, input preserved.
class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();
        for (int value : nums) {
            if (!seen.add(value)) {
                return true;
            }
        }
        return false;
    }
}

// Follow-up: O(n log n) time and O(log n) sorting-stack space, but no O(n) set.
// This version mutates nums.
class SortingSolution {
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return true;
            }
        }
        return false;
    }
}

