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
