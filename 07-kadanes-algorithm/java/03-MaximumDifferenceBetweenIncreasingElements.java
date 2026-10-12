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

