// 226. Invert Binary Tree
// https://leetcode.com/problems/invert-binary-tree/
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
