/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd compute the total once, then track the sum strictly to the left.
 * - At each index the right sum is total - left - current; I'd check before adding the current
 *   value to the left sum.
 * Time complexity: O(n) for two scans.
 * Space complexity: O(1) auxiliary space.
 */

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

