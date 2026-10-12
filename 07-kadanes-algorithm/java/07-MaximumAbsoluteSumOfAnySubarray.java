// O(n) time and O(1) space using prefix extrema.
class Solution {
    public int maxAbsoluteSum(int[] nums) {
        int prefix = 0;
        int minimumPrefix = 0;
        int maximumPrefix = 0;

        for (int value : nums) {
            prefix += value;
            minimumPrefix = Math.min(minimumPrefix, prefix);
            maximumPrefix = Math.max(maximumPrefix, prefix);
        }
        return maximumPrefix - minimumPrefix;
    }
}

