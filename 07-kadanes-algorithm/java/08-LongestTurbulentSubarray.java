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

