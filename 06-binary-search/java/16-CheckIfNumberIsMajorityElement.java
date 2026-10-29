/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd find the target's first and last occurrences in the sorted array.
 * - Their inclusive count must be strictly greater than half the length; exactly half is
 *   insufficient.
 * Time complexity: O(log(n + 1)) for n values.
 * Space complexity: O(1) auxiliary space.
 */

// 1150. Check If a Number Is Majority Element in a Sorted Array
// https://leetcode.com/problems/check-if-a-number-is-majority-element-in-a-sorted-array/
/*
 * TYPE: MINIMIZATION for first index (>= target), MAXIMIZATION for last (<= target).
 * BOUNDS: lo=-1, hi=n; return whether last-first+1 > n/2 after checking presence.
 */
// O(log(n + 1)) time, O(1) auxiliary space. LeetCode Premium.
class Solution {
    public boolean isMajorityElement(int[] nums, int target) {
        int first = firstIndex(nums, target);
        if (first == nums.length || nums[first] != target) {
            return false;
        }
        int last = lastIndex(nums, target);
        return last - first + 1 > nums.length / 2;
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
