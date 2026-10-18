/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd return one extendable branch gain from each subtree, ignoring negative child gains.
 * - At each node I'd separately test a complete path using both children; a negative initial
 *   best preserves all-negative answers.
 * Time complexity: O(n) for n nodes.
 * Space complexity: O(h) recursive stack for height h.
 */

// 124. Binary Tree Maximum Path Sum
// https://leetcode.com/problems/binary-tree-maximum-path-sum/
// NeetCode 150: https://neetcode.io/problems/binary-tree-maximum-path-sum/question?list=neetcode150
// Solution resets state on every call; tree/Kadane-style hybrid.
import java.util.*;

class Solution {
    private int best;

    public int maxPathSum(TreeNode root) {
        best = Integer.MIN_VALUE;
        gain(root);
        return best;
    }

    private int gain(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int left = Math.max(0, gain(node.left)), right = Math.max(0, gain(node.right));
        best = Math.max(best, node.val + left + right);
        return node.val + Math.max(left, right);
    }
}
