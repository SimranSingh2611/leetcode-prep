// 1373. Maximum Sum BST in Binary Tree
// https://leetcode.com/problems/maximum-sum-bst-in-binary-tree/
// Solution iterative postorder; general-tree input, BST-subtree objective.
import java.util.*;

class Solution {
    public int maxSumBST(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Deque<TreeNode> stack = new ArrayDeque<>(), order = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            order.push(node);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        Map<TreeNode, long[]> info = new IdentityHashMap<>();
        long[] empty = {1, Long.MAX_VALUE, Long.MIN_VALUE, 0};
        long best = 0;
        while (!order.isEmpty()) {
            TreeNode node = order.pop();
            long[] left = info.getOrDefault(node.left, empty),
                    right = info.getOrDefault(node.right, empty);
            if (left[0] == 1 && right[0] == 1 && left[2] < node.val && node.val < right[1]) {
                long sum = left[3] + right[3] + node.val;
                best = Math.max(best, sum);
                info.put(
                        node,
                        new long[] {
                            1, Math.min(left[1], node.val), Math.max(right[2], node.val), sum
                        });
            } else {
                info.put(node, new long[] {0, 0, 0, 0});
            }
        }
        return (int) best;
    }
}
