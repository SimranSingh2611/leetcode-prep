/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd compute the first length-k sum and slide by adding the entering value and subtracting
 *   the leaving one.
 * - All windows have the same size, so maximizing their sum also maximizes their average.
 * Time complexity: O(n) for n values.
 * Space complexity: O(1) auxiliary space.
 */

// O(n) time and O(1) space.
class Solution {
    public double findMaxAverage(int[] nums, int k) {
        long windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += nums[i];
        }

        long maximumSum = windowSum;
        for (int right = k; right < nums.length; right++) {
            windowSum += nums[right] - nums[right - k];
            maximumSum = Math.max(maximumSum, windowSum);
        }
        return (double) maximumSum / k;
    }
}

