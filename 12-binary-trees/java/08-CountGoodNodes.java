/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd pass the largest value seen on the root-to-node path into each call.
 * - A node is good when its value is at least that maximum, and each child receives the updated
 *   path state.
 * Time complexity: O(n) for n nodes.
 * Space complexity: O(h) recursive stack for height h.
 */

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
