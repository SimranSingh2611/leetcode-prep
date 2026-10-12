// 103. Binary Tree Zigzag Level Order Traversal
// https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
// O(n) time, O(w) auxiliary space plus O(n) output. Input preserved.
import java.util.*;

class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        boolean leftToRight = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            Integer[] level = new Integer[size];
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                level[leftToRight ? i : size - 1 - i] = node.val;
                // Keep traversal order stable; only output placement alternates.
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            result.add(Arrays.asList(level));
            leftToRight = !leftToRight;
        }
        return result;
    }
}
