/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd return a subtree height when balanced and minus one when unbalanced.
 * - Combining balance checking with height calculation avoids rescanning subtrees, and the
 *   failure marker propagates immediately.
 * Time complexity: O(n) for n nodes.
 * Space complexity: O(h) recursive stack for height h.
 */

// 110. Balanced Binary Tree
// https://leetcode.com/problems/balanced-binary-tree/
// NeetCode 150: https://neetcode.io/problems/balanced-binary-tree/question?list=neetcode150
// O(n) time, O(h) recursion stack. Input preserved.
class Solution {
    public boolean isBalanced(TreeNode root) {
        return heightOrUnbalanced(root) != -1;
    }

    // Return height for a balanced subtree; -1 propagates any imbalance.
    private int heightOrUnbalanced(TreeNode node) {
        if (node == null) return 0;
        int left = heightOrUnbalanced(node.left);
        if (left == -1) return -1;
        int right = heightOrUnbalanced(node.right);
        if (right == -1 || Math.abs(left - right) > 1) return -1;
        return 1 + Math.max(left, right);
    }
}
