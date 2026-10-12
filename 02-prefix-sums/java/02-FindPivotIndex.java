// O(n) time and O(1) space.
class Solution {
    public int pivotIndex(int[] nums) {
        long total = 0;
        for (int value : nums) {
            total += value;
        }

        long leftSum = 0;
        for (int i = 0; i < nums.length; i++) {
            long rightSum = total - leftSum - nums[i];
            if (leftSum == rightSum) {
                return i;
            }
            leftSum += nums[i];
        }
        return -1;
    }
}

