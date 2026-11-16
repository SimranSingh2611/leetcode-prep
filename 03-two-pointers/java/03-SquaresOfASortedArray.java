/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - The largest remaining square comes from one of the two endpoints.
 * - I'd compare their magnitudes and fill the result from right to left, avoiding another sort.
 * Time complexity: O(n) for n values.
 * Space complexity: O(1) auxiliary pointer state, plus O(n) output.
 */

// 977. Squares of a Sorted Array
// https://leetcode.com/problems/squares-of-a-sorted-array/
// O(n) time and O(n) output space.
class Solution {
    public int[] sortedSquares(int[] nums) {
        int left = 0, right = nums.length - 1;
        int[] out = new int[nums.length];

        for(int i=nums.length-1; i>=0; i--) {
            int val;
            if (Math.abs(nums[left]) > Math.abs(nums[right])) {
                val = nums[left];
                left++;
            } else {
                val = nums[right];
                right--;
            }
            out[i] = val * val;
        }

        return out;
    }
}