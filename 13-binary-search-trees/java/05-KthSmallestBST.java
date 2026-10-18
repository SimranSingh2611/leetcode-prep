/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd perform inorder traversal and count nodes as they are popped.
 * - The kth visited value is the kth smallest, so I can stop without building the full sorted
 *   list.
 * Time complexity: O(h + k) for tree height h and requested rank k.
 * Space complexity: O(h) auxiliary traversal stack.
 */

// 230. Kth Smallest Element in a BST
// https://leetcode.com/problems/kth-smallest-element-in-a-bst/
// NeetCode 150: https://neetcode.io/problems/kth-smallest-integer-in-bst/question?list=neetcode150
// Solution; k is valid; no mutation.
import java.util.*;

class Solution {
    public int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (--k == 0) {
                return root.val;
            }
            root = root.right;
        }
        throw new IllegalArgumentException("Invalid k");
    }
}
