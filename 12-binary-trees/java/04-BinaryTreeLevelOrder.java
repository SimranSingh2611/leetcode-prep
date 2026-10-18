/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd process the tree with a queue and freeze its size at the start of each level.
 * - Removing exactly that many nodes keeps newly enqueued children in the next result row.
 * Time complexity: O(n) for n nodes.
 * Space complexity: O(w) auxiliary queue for maximum level width w, plus O(n) output.
 */

// 102. Binary Tree Level Order Traversal
// https://leetcode.com/problems/binary-tree-level-order-traversal/
// NeetCode 150: https://neetcode.io/problems/level-order-traversal-of-binary-tree/question?list=neetcode150
// Solution; input preserved.
import java.util.*;

class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> out = new ArrayList<>();
        if (root == null) {
            return out;
        }
        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> level = new ArrayList<>();
            while (size-- > 0) {
                TreeNode n = q.remove();
                level.add(n.val);
                if (n.left != null) {
                    q.add(n.left);
                }
                if (n.right != null) {
                    q.add(n.right);
                }
            }
            out.add(level);
        }
        return out;
    }
}
