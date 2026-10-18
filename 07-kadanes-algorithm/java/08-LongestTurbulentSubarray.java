/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd keep the lengths of alternating runs ending with an upward or downward comparison.
 * - Each strict comparison extends the opposite state, while equal neighbors reset both
 *   lengths.
 * Time complexity: O(n) for n values.
 * Space complexity: O(1) auxiliary space.
 */

// 978. Longest Turbulent Subarray
// https://leetcode.com/problems/longest-turbulent-subarray/
// O(n) time and O(1) space.
class Solution {
    public int maxTurbulenceSize(int[] arr) {
        int increasing = 1;
        int decreasing = 1;
        int longest = 1;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[i - 1]) {
                increasing = decreasing + 1;
                decreasing = 1;
            } else if (arr[i] < arr[i - 1]) {
                decreasing = increasing + 1;
                increasing = 1;
            } else {
                increasing = 1;
                decreasing = 1;
            }
            longest = Math.max(longest, Math.max(increasing, decreasing));
        }
        return longest;
    }
}

