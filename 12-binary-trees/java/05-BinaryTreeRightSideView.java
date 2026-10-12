// 199. Binary Tree Right Side View
// https://leetcode.com/problems/binary-tree-right-side-view/
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
