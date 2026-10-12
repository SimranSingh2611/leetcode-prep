// 162. Find Peak Element
// https://leetcode.com/problems/find-peak-element/
/*
 * TYPE: ANY PEAK -- minimization-style updates; slope is not globally monotonic.
 * BOUNDS: lo=-1 (rising edge), hi=n-1 (falling edge); return hi.
 */
// O(log(n + 1)) time, O(1) auxiliary space. Adjacent values must differ.
class Solution {
    public int findPeakElement(int[] nums) {
        int lo = -1;
        int hi = nums.length - 1;
        while (lo + 1 < hi) {
            int mid = lo + (int) (((long) hi - lo) / 2);
            if (nums[mid] > nums[mid + 1]) {
                hi = mid;
            } else {
                lo = mid;
            }
        }
        return hi;
    }
}
