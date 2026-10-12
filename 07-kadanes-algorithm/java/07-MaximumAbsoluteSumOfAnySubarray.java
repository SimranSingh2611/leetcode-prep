/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - Every subarray sum is a difference between two prefix sums.
 * - I'd track the smallest and largest prefixes; their difference is the greatest absolute
 *   subarray sum, regardless of which appears first.
 * Time complexity: O(n) for n values.
 * Space complexity: O(1) auxiliary space.
 */

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

