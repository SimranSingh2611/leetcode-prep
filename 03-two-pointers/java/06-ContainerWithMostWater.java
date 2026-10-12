/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd measure the container formed by the current endpoints.
 * - Only moving the shorter wall can improve the limiting height, since every move reduces the
 *   width.
 * Time complexity: O(n) for n heights.
 * Space complexity: O(1) auxiliary space.
 */

// O(n) time and O(1) space.
class Solution {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        long best = 0;

        while (left < right) {
            long area = (long) (right - left) * Math.min(height[left], height[right]);
            best = Math.max(best, area);
            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return (int) best;
    }
}

