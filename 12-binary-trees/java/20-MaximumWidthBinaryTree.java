/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd BFS with conceptual complete-tree positions so gaps between actual nodes contribute to
 *   width.
 * - Subtracting the first position at each level preserves those gaps while keeping indices
 *   small.
 * Time complexity: O(n) for n actual nodes.
 * Space complexity: O(w) auxiliary queue, where w is the maximum number of actual nodes on a
 *   level, rather than its width including gaps.
 */

// 662. Maximum Width of Binary Tree
// https://leetcode.com/problems/maximum-width-of-binary-tree/
// O(n) time, O(w) queue for maximum number of actual nodes on a level.
// Input preserved. Normalize each level; use long for intermediate indices.
import java.util.*;

class Solution {
    private static class PositionedNode {
        final TreeNode node;
        final long index;

        PositionedNode(TreeNode node, long index) {
            this.node = node;
            this.index = index;
        }
    }

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        Queue<PositionedNode> queue = new ArrayDeque<>();
        queue.add(new PositionedNode(root, 0L));
        long best = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            long base = queue.peek().index;
            long last = 0;
            for (int i = 0; i < size; i++) {
                PositionedNode current = queue.remove();
                last = current.index - base;
                if (current.node.left != null) {
                    queue.add(new PositionedNode(current.node.left, 2 * last));
                }
                if (current.node.right != null) {
                    queue.add(new PositionedNode(current.node.right, 2 * last + 1));
                }
            }
            best = Math.max(best, last + 1);
        }
        return (int) best; // The problem guarantees the answer fits a signed int.
    }
}
