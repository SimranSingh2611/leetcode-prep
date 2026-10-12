/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - The largest remaining square comes from one of the two endpoints.
 * - I'd compare their magnitudes and fill the result from right to left, avoiding another sort.
 * Time complexity: O(n) for n values.
 * Space complexity: O(1) auxiliary pointer state, plus O(n) output.
 */

// O(n) time and O(n) output space.
class Solution {
    public int[] sortedSquares(int[] nums) {
        int[] result = new int[nums.length];
        int left = 0;
        int right = nums.length - 1;

        for (int write = nums.length - 1; write >= 0; write--) {
            if (Math.abs((long) nums[left]) > Math.abs((long) nums[right])) {
                result[write] = nums[left] * nums[left];
                left++;
            } else {
                result[write] = nums[right] * nums[right];
                right--;
            }
        }
        return result;
    }
}

