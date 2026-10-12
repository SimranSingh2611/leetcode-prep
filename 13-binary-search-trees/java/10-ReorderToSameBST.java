/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - The first value fixes the root, while the left and right groups must keep valid internal
 *   reorderings.
 * - I'd multiply both subtree counts by the number of ways to interleave the groups, then
 *   exclude the original ordering.
 * Time complexity: O(n^2) for n values.
 * Space complexity: O(n^2) auxiliary binomial table and retained partitions, including an O(h)
 *   stack.
 */

// 1569. Number of Ways to Reorder Array to Get Same BST
// https://leetcode.com/problems/number-of-ways-to-reorder-array-to-get-same-bst/
// Solution; modular combinatorics stretch, input preserved.
import java.util.*;

class Solution {
    private static final long MOD = 1_000_000_007L;
    private long[][] choose;

    public int numOfWays(int[] nums) {
        int n = nums.length;
        choose = new long[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            choose[i][0] = choose[i][i] = 1;
            for (int j = 1; j < i; j++) {
                choose[i][j] = (choose[i - 1][j - 1] + choose[i - 1][j]) % MOD;
            }
        }
        List<Integer> values = new ArrayList<>();
        for (int x : nums) {
            values.add(x);
        }
        return (int) ((ways(values) - 1 + MOD) % MOD);
    }

    private long ways(List<Integer> a) {
        if (a.size() < 3) {
            return 1;
        }
        List<Integer> left = new ArrayList<>(), right = new ArrayList<>();
        int root = a.get(0);
        for (int i = 1; i < a.size(); i++) {
            if (a.get(i) < root) {
                left.add(a.get(i));
            } else {
                right.add(a.get(i));
            }
        }
        return choose[a.size() - 1][left.size()] * ways(left) % MOD * ways(right) % MOD;
    }
}
