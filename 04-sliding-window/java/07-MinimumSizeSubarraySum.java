/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - Positive values make the window sum monotonic as either boundary moves.
 * - I'd expand until the target is met, then shrink repeatedly while recording the shortest
 *   valid window.
 * Time complexity: O(n) amortized overall; each pointer advances at most n times.
 * Space complexity: O(1) auxiliary space.
 */

// O(n) time and O(1) space; relies on every nums value being positive.
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        long windowSum = 0;
        int shortest = nums.length + 1;

        for (int right = 0; right < nums.length; right++) {
            windowSum += nums[right];
            while (windowSum >= target) {
                shortest = Math.min(shortest, right - left + 1);
                windowSum -= nums[left++];
            }
        }
        return shortest == nums.length + 1 ? 0 : shortest;
    }
}

