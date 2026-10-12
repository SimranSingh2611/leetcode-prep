/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd locate the key using BST ordering and directly replace nodes with zero or one child.
 * - For two children, I'd copy the inorder successor's value and delete that successor from the
 *   right subtree.
 * Time complexity: O(h) for tree height h.
 * Space complexity: O(h) recursive stack.
 */

// 450. Delete Node in a BST
// https://leetcode.com/problems/delete-node-in-a-bst/
// Solution mutates values and links.
import java.util.*;

class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            }
            if (root.right == null) {
                return root.left;
            }
            TreeNode successor = root.right;
            while (successor.left != null) {
                successor = successor.left;
            }
            root.val = successor.val;
            root.right = deleteNode(root.right, successor.val);
        }
        return root;
    }
}
