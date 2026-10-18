/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - A valid strict BST has a strictly increasing inorder traversal.
 * - I'd iterate with a stack and reject any value no greater than the previous value, using a
 *   safe sentinel type.
 * Time complexity: O(n) for n nodes.
 * Space complexity: O(h) auxiliary traversal stack for height h.
 */

// 98. Validate Binary Search Tree
// https://leetcode.com/problems/validate-binary-search-tree/
// NeetCode 150: https://neetcode.io/problems/valid-binary-search-tree/question?list=neetcode150
// Solution iterative; no mutation.
import java.util.*;

class Solution {
    public boolean isValidBST(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        long previous = Long.MIN_VALUE;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.val <= previous) {
                return false;
            }
            previous = root.val;
            root = root.right;
        }
        return true;
    }
}
