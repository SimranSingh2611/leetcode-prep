/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd branch on including or excluding each value while carrying the current XOR.
 * - Summing at the leaves covers every subset; the follow-up uses the fact that each present
 *   bit appears in half the subsets.
 * Time complexity: Primary enumeration: O(2^n) for n values; bit-contribution alternative:
 *   O(n).
 * Space complexity: Primary: O(n) recursive stack; alternative: O(1) auxiliary space.
 */

// 1863. Sum of All Subset XOR Totals
// https://leetcode.com/problems/sum-of-all-subset-xor-totals/
// Solution teaches backtracking; BitContributionSolution is the faster follow-up. Neither mutates
// nums.
import java.util.*;

class Solution {
    public int subsetXORSum(int[] nums) {
        return sum(nums, 0, 0);
    }

    private int sum(int[] a, int i, int xor) {
        if (i == a.length) {
            return xor;
        }
        return sum(a, i + 1, xor) + sum(a, i + 1, xor ^ a[i]);
    }
}

class BitContributionSolution {
    public int subsetXORSum(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int bits = 0;
        for (int x : nums) {
            bits |= x;
        }
        return bits * (1 << (nums.length - 1));
    }
}
