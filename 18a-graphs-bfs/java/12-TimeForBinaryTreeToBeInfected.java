/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd build parent links and locate the infection's starting node.
 * - BFS over the resulting undirected tree spreads infection one edge per minute, so the final
 *   layer gives the total time.
 * Time complexity: O(n) for n nodes.
 * Space complexity: O(n) auxiliary parent/visited/queue storage.
 */

// 2385. Amount of Time for Binary Tree to Be Infected
// https://leetcode.com/problems/amount-of-time-for-binary-tree-to-be-infected/
// Solution preserves the tree. The problem guarantees unique values and that start exists.
import java.util.*;

class Solution {
    public int amountOfTime(TreeNode root, int start) {
        Map<TreeNode, TreeNode> parent = new IdentityHashMap<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        TreeNode source = null;
        parent.put(root, null);
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.val == start) {
                source = node;
            }
            if (node.left != null) {
                parent.put(node.left, node);
                queue.offer(node.left);
            }
            if (node.right != null) {
                parent.put(node.right, node);
                queue.offer(node.right);
            }
        }
        Set<TreeNode> seen = Collections.newSetFromMap(new IdentityHashMap<TreeNode, Boolean>());
        queue.offer(source);
        seen.add(source);
        int minutes = -1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            minutes++;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                for (TreeNode next : new TreeNode[] {node.left, node.right, parent.get(node)}) {
                    if (next != null && seen.add(next)) {
                        queue.offer(next);
                    }
                }
            }
        }
        return minutes;
    }
}
