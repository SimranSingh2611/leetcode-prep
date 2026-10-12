// O(n) time and O(1) space.
class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int total = nums[0];
        int maximumEnding = nums[0];
        int maximum = nums[0];
        int minimumEnding = nums[0];
        int minimum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int value = nums[i];
            total += value;
            maximumEnding = Math.max(value, maximumEnding + value);
            maximum = Math.max(maximum, maximumEnding);
            minimumEnding = Math.min(value, minimumEnding + value);
            minimum = Math.min(minimum, minimumEnding);
        }

        if (maximum < 0) {
            return maximum;
        }
        return Math.max(maximum, total - minimum);
    }
}

