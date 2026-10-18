/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd carry a running total from left to right.
 * - Writing it back into each position builds the prefix sums in place.
 * Time complexity: O(n) for n values.
 * Space complexity: O(1) auxiliary space; the input becomes the result.
 */

// 1480. Running Sum of 1d Array
// https://leetcode.com/problems/running-sum-of-1d-array/
// O(n) time and O(1) extra space; mutates nums.
class Solution {
    public int[] runningSum(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            nums[i] += nums[i - 1];
        }
        return nums;
    }
}

