// 540. Single Element in a Sorted Array
// https://leetcode.com/problems/single-element-in-a-sorted-array/
/*
 * TYPE: MINIMIZATION -- first index whose even/odd pair alignment breaks.
 * CONDITION: nums[i] != nums[i ^ 1]; last index is a true endpoint, never tested.
 * BOUNDS: lo=-1 (false), hi=n-1 (true); return nums[hi].
 */
// O(log(n + 1)) time, O(1) auxiliary space.
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int lo = -1;
        int hi = nums.length - 1;
        while (lo + 1 < hi) {
            int mid = lo + (int) (((long) hi - lo) / 2);
            if (nums[mid] != nums[mid ^ 1]) {
                hi = mid;
            } else {
                lo = mid;
            }
        }
        return nums[hi];
    }
}
