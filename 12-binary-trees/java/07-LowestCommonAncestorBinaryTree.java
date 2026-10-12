/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd let each subtree return a target it contains or the ancestor already found inside it.
 * - If both child calls return a target, the current node is their split point; otherwise I'd
 *   propagate the non-null result.
 * Time complexity: O(n) worst case for n nodes.
 * Space complexity: O(h) recursive stack for height h.
 */

// 236. Lowest Common Ancestor of a Binary Tree
// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
// Solution; compare node references, not values.
import java.util.*;

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q),
                right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
    }
}
