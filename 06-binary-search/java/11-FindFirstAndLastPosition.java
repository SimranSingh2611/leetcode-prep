/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd search separately for the first value at least the target and the last value at most
 *   it.
 * - After verifying presence, those boundaries give the inclusive range without computing
 *   overflow-prone target + 1.
 * Time complexity: O(log(n + 1)) for n values.
 * Space complexity: O(1) auxiliary space.
 */

// 34. Find First and Last Position of Element in Sorted Array
// https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
/*
 * TYPE: MINIMIZATION for first index (>= target), MAXIMIZATION for last (<= target).
 * BOUNDS: lo=-1, hi=n in both loops; virtual endpoints stay unread.
 */
// O(log(n + 1)) time, O(1) auxiliary space.
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int first = firstIndex(nums, target);
        if (first == nums.length || nums[first] != target) {
            return new int[]{-1, -1};
        }
        return new int[]{first, lastIndex(nums, target)};
    }

    private int firstIndex(int[] nums, int target) {
        int lo = -1;
        int hi = nums.length;
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] >= target) {
                hi = mid;
            } else {
                lo = mid;
            }
        }
        return hi;
    }

    private int lastIndex(int[] nums, int target) {
        int lo = -1;
        int hi = nums.length;
        while (lo + 1 < hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] <= target) {
                lo = mid;
            } else {
                hi = mid;
            }
        }
        return lo;
    }
}
