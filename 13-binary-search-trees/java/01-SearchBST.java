/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd use the BST ordering to choose just one child at each step.
 * - A matching node is already the root of the requested subtree; falling off the tree means
 *   absence.
 * Time complexity: O(h) for height h: O(log n) when balanced, O(n) when skewed.
 * Space complexity: O(1) auxiliary space for the iterative walk.
 */

// 700. Search in a Binary Search Tree
// https://leetcode.com/problems/search-in-a-binary-search-tree/
// Solution iterative; no mutation.
import java.util.*;

class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        while (root != null && root.val != val) {
            root = val < root.val ? root.left : root.right;
        }
        return root;
    }
}
