// 1448. Count Good Nodes in Binary Tree
// https://leetcode.com/problems/count-good-nodes-in-binary-tree/
// Solution; input preserved.
import java.util.*;

class Solution {
    public int goodNodes(TreeNode root) {
        return count(root, Integer.MIN_VALUE);
    }

    private int count(TreeNode node, int max) {
        if (node == null) {
            return 0;
        }
        int good = node.val >= max ? 1 : 0;
        max = Math.max(max, node.val);
        return good + count(node.left, max) + count(node.right, max);
    }
}
