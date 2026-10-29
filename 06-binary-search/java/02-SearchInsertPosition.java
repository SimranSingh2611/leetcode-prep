/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd use a lower-bound search for the first value at least the target.
 * - Its boundary is the insertion position even when the target is absent, including position
 *   n.
 * Time complexity: O(log(n + 1)) for n values.
 * Space complexity: O(1) auxiliary space.
 */

// 35. Search Insert Position
// https://leetcode.com/problems/search-insert-position/
/*
 * TYPE: MINIMIZATION -- earliest insertion index (nums[i] >= target).
 * BOUNDS: lo=-1 (false), hi=n (virtual true); return hi, including n.
 */
// O(log(n + 1)) time, O(1) auxiliary space. Does not mutate nums.
class Solution {
    public int searchInsert(int[] nums, int target) {
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
}
