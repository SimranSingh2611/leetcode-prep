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

