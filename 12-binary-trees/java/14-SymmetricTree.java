// 101. Symmetric Tree
// https://leetcode.com/problems/symmetric-tree/
// O(n) time, O(h) recursion stack. Input preserved.
class Solution {
    public boolean isSymmetric(TreeNode root) {
        return root == null || mirror(root.left, root.right);
    }

    private boolean mirror(TreeNode a, TreeNode b) {
        if (a == null || b == null) return a == b;
        return a.val == b.val && mirror(a.left, b.right) && mirror(a.right, b.left);
    }
}
