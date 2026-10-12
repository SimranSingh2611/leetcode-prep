/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - For a node with a left subtree, I'd attach its old right subtree to the left subtree's
 *   rightmost node.
 * - Then I'd move the left subtree to the right and continue, preserving preorder while
 *   rewiring in place.
 * Time complexity: O(n) amortized overall; predecessor scans traverse disjoint original right-
 *   spine segments.
 * Space complexity: O(1) auxiliary space; rewires the input tree.
 */

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
