/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd maintain the sum of the strictly ascending run ending at the current position.
 * - A larger next value extends that run; otherwise I'd restart and update the global maximum.
 * Time complexity: O(n) for n values.
 * Space complexity: O(1) auxiliary space.
 */

// O(n) time and O(1) space.
class Solution {
    public int maxAscendingSum(int[] nums) {
        int currentSum = nums[0];
        int maximumSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currentSum = nums[i] > nums[i - 1] ? currentSum + nums[i] : nums[i];
            maximumSum = Math.max(maximumSum, currentSum);
        }
        return maximumSum;
    }
}

