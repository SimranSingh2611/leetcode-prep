// Recommended: O(n) time and O(1) space.
class Solution {
    public int trap(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int leftMaximum = 0;
        int rightMaximum = 0;
        int water = 0;

        while (left <= right) {
            if (leftMaximum <= rightMaximum) {
                leftMaximum = Math.max(leftMaximum, height[left]);
                water += leftMaximum - height[left];
                left++;
            } else {
                rightMaximum = Math.max(rightMaximum, height[right]);
                water += rightMaximum - height[right];
                right--;
            }
        }
        return water;
    }
}

// Teaching alternative: O(n) time and O(n) space with explicit boundary maxima.
class PrefixSuffixSolution {
    public int trap(int[] height) {
        if (height.length == 0) {
            return 0;
        }
        int n = height.length;
        int[] leftMaximum = new int[n];
        int[] rightMaximum = new int[n];
        leftMaximum[0] = height[0];
        rightMaximum[n - 1] = height[n - 1];

        for (int i = 1; i < n; i++) {
            leftMaximum[i] = Math.max(leftMaximum[i - 1], height[i]);
        }
        for (int i = n - 2; i >= 0; i--) {
            rightMaximum[i] = Math.max(rightMaximum[i + 1], height[i]);
        }

        int water = 0;
        for (int i = 0; i < n; i++) {
            water += Math.min(leftMaximum[i], rightMaximum[i]) - height[i];
        }
        return water;
    }
}

