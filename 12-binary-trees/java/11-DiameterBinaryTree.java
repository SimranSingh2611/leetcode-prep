// 543. Diameter of Binary Tree
// https://leetcode.com/problems/diameter-of-binary-tree/
// O(n) time, O(h) recursion stack. Input preserved; state resets per call.
class Solution {
    private int diameter;

    public int diameterOfBinaryTree(TreeNode root) {
        diameter = 0;
        height(root);
        return diameter;
    }

    private int height(TreeNode node) {
        if (node == null) return 0;
        int left = height(node.left);
        int right = height(node.right);
        // Child heights count nodes, so their sum counts edges through node.
        diameter = Math.max(diameter, left + right);
        return 1 + Math.max(left, right);
    }
}
