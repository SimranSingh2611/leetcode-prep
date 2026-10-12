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

