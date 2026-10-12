// 114. Flatten Binary Tree to Linked List
// https://leetcode.com/problems/flatten-binary-tree-to-linked-list/
// O(n) amortized time, O(1) auxiliary space. Mutates the original node links.
class Solution {
    public void flatten(TreeNode root) {
        TreeNode node = root;
        while (node != null) {
            if (node.left != null) {
                TreeNode predecessor = node.left;
                while (predecessor.right != null) predecessor = predecessor.right;
                // Preserve the old right subtree after the moved left subtree.
                predecessor.right = node.right;
                node.right = node.left;
                node.left = null;
            }
            node = node.right;
        }
    }
}
