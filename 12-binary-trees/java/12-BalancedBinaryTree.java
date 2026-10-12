// 110. Balanced Binary Tree
// https://leetcode.com/problems/balanced-binary-tree/
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
