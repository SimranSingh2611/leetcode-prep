// 572. Subtree of Another Tree
// https://leetcode.com/problems/subtree-of-another-tree/
// O(n*m) worst-case time, O(hRoot + hSubRoot) stack. Inputs preserved.
class Solution {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (subRoot == null) return true;
        if (root == null) return false;
        return same(root, subRoot)
            || isSubtree(root.left, subRoot)
            || isSubtree(root.right, subRoot);
    }

    private boolean same(TreeNode a, TreeNode b) {
        if (a == null || b == null) return a == b;
        return a.val == b.val && same(a.left, b.left) && same(a.right, b.right);
    }
}
