// Recommended Kadane recurrence: O(n) time and O(1) space.
class Solution {
    public int maxSubArray(int[] nums) {
        int bestEndingHere = nums[0];
        int bestOverall = nums[0];

        for (int i = 1; i < nums.length; i++) {
            bestEndingHere = Math.max(nums[i], bestEndingHere + nums[i]);
            bestOverall = Math.max(bestOverall, bestEndingHere);
        }
        return bestOverall;
    }
}

// Equivalent follow-up: current prefix minus the smallest earlier prefix.
class PrefixMinimumSolution {
    public int maxSubArray(int[] nums) {
        long prefix = 0;
        long minimumPrefix = 0;
        long best = Long.MIN_VALUE;
        for (int value : nums) {
            prefix += value;
            best = Math.max(best, prefix - minimumPrefix);
            minimumPrefix = Math.min(minimumPrefix, prefix);
        }
        return (int) best;
    }
}

