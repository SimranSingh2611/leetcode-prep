/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd keep the smallest earlier value as a potential first element.
 * - I'd update the difference only when the current value is strictly larger, preserving the
 *   required index and value ordering.
 * Time complexity: O(n) for n values.
 * Space complexity: O(1) auxiliary space.
 */

// 2016. Maximum Difference Between Increasing Elements
// https://leetcode.com/problems/maximum-difference-between-increasing-elements/
// O(n) time and O(1) space.
class Solution {
    public int maximumDifference(int[] nums) {
        int minimum = nums[0];
        int maximumDifference = -1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > minimum) {
                maximumDifference = Math.max(maximumDifference, nums[i] - minimum);
            }
            minimum = Math.min(minimum, nums[i]);
        }
        return maximumDifference;
    }
}

