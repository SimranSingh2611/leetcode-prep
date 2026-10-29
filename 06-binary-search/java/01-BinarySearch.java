/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd find the first index whose value is at least the target using false/true endpoints.
 * - Then I'd verify equality and check the virtual n endpoint before indexing.
 * Time complexity: O(log(n + 1)) for n values.
 * Space complexity: O(1) auxiliary space.
 */

// 704. Binary Search
// https://leetcode.com/problems/binary-search/
// NeetCode 150: https://neetcode.io/problems/binary-search/question?list=neetcode150
/*
 * TYPE: MINIMIZATION -- first index with nums[i] >= target.
 * BOUNDS: lo=-1 (false), hi=n (virtual true); sentinels stay unread.
 * RESULT: hi if in range and equal; otherwise -1.
 */
// O(log(n + 1)) time, O(1) auxiliary space. Does not mutate nums.
class Solution {
    public int search(int[] nums, int target) {
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
        // A lower bound is not necessarily an exact match; n is not an array index.
        return hi < nums.length && nums[hi] == target ? hi : -1;
    }
}
