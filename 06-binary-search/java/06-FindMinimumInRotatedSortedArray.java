/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd compare every candidate with the fixed final array value.
 * - With distinct values, the first value no greater than that reference starts the low segment
 *   and is the minimum.
 * Time complexity: O(log(n + 1)) for n values.
 * Space complexity: O(1) auxiliary space.
 */

// 153. Find Minimum in Rotated Sorted Array
// https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
// NeetCode 150: https://neetcode.io/problems/find-minimum-in-rotated-sorted-array/question?list=neetcode150
/*
 * TYPE: MINIMIZATION -- first index with nums[i] <= fixed last value.
 * BOUNDS: lo=-1 (false), hi=n-1 (true); return nums[hi].
 */
// O(log(n + 1)) time, O(1) auxiliary space. Does not mutate nums.
class Solution {
    public int findMin(int[] nums) {
        int lo = -1;
        int hi = nums.length - 1; // A real, known-true endpoint.
        int last = nums[nums.length - 1];
        while (lo + 1 < hi) {
            int mid = lo + (int) (((long) hi - lo) / 2);
            if (nums[mid] <= last) {
                hi = mid;
            } else {
                lo = mid;
            }
        }
        return nums[hi]; // The judge requests the value, not its index.
    }
}
