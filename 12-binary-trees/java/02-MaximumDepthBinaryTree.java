// 104. Maximum Depth of Binary Tree
// https://leetcode.com/problems/maximum-depth-of-binary-tree/
// Solution recursive; BreadthFirstSolution avoids stack-depth risk, not always less memory.
import java.util.*;

class Solution {
    public int maxDepth(TreeNode root) {
        return root == null ? 0 : 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}

class BreadthFirstSolution {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        int depth = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            depth++;
            while (size-- > 0) {
                TreeNode n = q.remove();
                if (n.left != null) {
                    q.add(n.left);
                }
                if (n.right != null) {
                    q.add(n.right);
                }
            }
        }
        return depth;
    }
}
