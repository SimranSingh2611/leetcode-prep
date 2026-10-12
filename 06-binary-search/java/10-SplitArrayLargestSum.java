/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd search a candidate maximum subarray sum and greedily count how many parts it needs.
 * - Nonnegative values make feasibility monotonic, and a split into at most k parts can be
 *   refined to exactly k.
 * Time complexity: O(n(1 + log(S - M + 1))) for S = sum(nums), M = max(nums), including the
 *   initial scan.
 * Space complexity: O(1) auxiliary space.
 */

// 410. Split Array Largest Sum
// https://leetcode.com/problems/split-array-largest-sum/
/*
 * TYPE: MINIMIZATION -- smallest sum limit needing at most k parts.
 * BOUNDS: lo=max(nums)-1 (infeasible), hi=sum(nums) (feasible), both long.
 */
// O(n * (1 + log(sum - max + 1))) time, O(1) auxiliary space; input unchanged.
class Solution {
    public int splitArray(int[] nums, int k) {
        long max = 0;
        long sum = 0;
        for (int value : nums) {
            max = Math.max(max, value);
            sum += value;
        }
        long lo = max - 1; // Known infeasible, even for all-zero arrays (lo=-1).
        long hi = sum;     // Known feasible with at most k parts.
        while (lo + 1 < hi) {
            long mid = lo + (hi - lo) / 2;
            if (requiredParts(nums, mid) <= k) {
                hi = mid;
            } else {
                lo = mid;
            }
        }
        return (int) hi; // LeetCode's bounds keep the answer within int.
    }

    private int requiredParts(int[] nums, long limit) {
        int parts = 1;
        long currentSum = 0;
        for (int value : nums) {
            if (currentSum + value > limit) {
                parts++;
                currentSum = value;
            } else {
                currentSum += value;
            }
        }
        return parts;
    }
}
