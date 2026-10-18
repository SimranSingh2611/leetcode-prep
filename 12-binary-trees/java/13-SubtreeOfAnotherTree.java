/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd try each node in the larger tree as a possible subtree root.
 * - An exact paired comparison must match values and null positions, since a matching value
 *   sequence alone is insufficient.
 * Time complexity: O(nm) worst case for larger-tree size n and candidate-tree size m.
 * Space complexity: O(h1 + h2) is a safe recursive-stack bound for the two tree heights.
 */

// 572. Subtree of Another Tree
// https://leetcode.com/problems/subtree-of-another-tree/
// NeetCode 150: https://neetcode.io/problems/subtree-of-a-binary-tree/question?list=neetcode150
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
