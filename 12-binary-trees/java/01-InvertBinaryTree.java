/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd invert the two child subtrees recursively and swap their returned roots.
 * - Null is the base case, and each node's swap completes its part of the transformation.
 * Time complexity: O(n) for n nodes.
 * Space complexity: O(h) recursive stack for height h; O(n) on a chain.
 */

// 226. Invert Binary Tree
// https://leetcode.com/problems/invert-binary-tree/
// NeetCode 150: https://neetcode.io/problems/invert-a-binary-tree/question?list=neetcode150
// Solution mutates the tree.
import java.util.*;

class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = invertTree(root.left), right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }
}
