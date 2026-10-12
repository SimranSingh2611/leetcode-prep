// 98. Validate Binary Search Tree
// https://leetcode.com/problems/validate-binary-search-tree/
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
