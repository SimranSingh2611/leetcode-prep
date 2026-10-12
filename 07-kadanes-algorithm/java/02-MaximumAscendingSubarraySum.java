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

