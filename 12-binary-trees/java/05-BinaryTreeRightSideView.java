/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd visit right children before left children in DFS.
 * - The first node reached at each depth is the visible one, so I'd record only previously
 *   unseen depths.
 * Time complexity: O(n) for n nodes.
 * Space complexity: O(h) auxiliary stack and O(h) output for tree height h.
 */

// 199. Binary Tree Right Side View
// https://leetcode.com/problems/binary-tree-right-side-view/
// NeetCode 150: https://neetcode.io/problems/binary-tree-right-side-view/question?list=neetcode150
// Solution; input preserved.
import java.util.*;

class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> out = new ArrayList<>();
        visit(root, 0, out);
        return out;
    }

    private void visit(TreeNode node, int depth, List<Integer> out) {
        if (node == null) {
            return;
        }
        if (depth == out.size()) {
            out.add(node.val);
        }
        visit(node.right, depth + 1, out);
        visit(node.left, depth + 1, out);
    }
}
