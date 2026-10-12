/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd use BFS and snapshot the queue size for each level.
 * - Processing exactly that many nodes groups their values together while leaving their
 *   children for the next level.
 * Time complexity: O(n) for n nodes.
 * Space complexity: O(w) auxiliary queue for maximum level width w, plus O(n) output.
 */

// 429. N-ary Tree Level Order Traversal
// https://leetcode.com/problems/n-ary-tree-level-order-traversal/
// Solution preserves the N-ary tree. LeetCode supplies Node with val and children.
import java.util.*;

class Solution {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                level.add(node.val);
                for (Node child : node.children) {
                    queue.offer(child);
                }
            }
            result.add(level);
        }
        return result;
    }
}
