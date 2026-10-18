/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd move left if both targets are smaller, and right if both are larger.
 * - The first split point, including a target equal to the current node, is their lowest common
 *   ancestor.
 * Time complexity: O(h) for height h: O(log n) balanced, O(n) skewed.
 * Space complexity: O(1) auxiliary space for the iterative walk.
 */

// 235. Lowest Common Ancestor of a Binary Search Tree
// https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/
// NeetCode 150: https://neetcode.io/problems/lowest-common-ancestor-in-binary-search-tree/question?list=neetcode150
// Solution; distinct target nodes are guaranteed present.
import java.util.*;

class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            if (p.val < root.val && q.val < root.val) {
                root = root.left;
            } else if (p.val > root.val && q.val > root.val) {
                root = root.right;
            } else {
                return root;
            }
        }
        return null;
    }
}
