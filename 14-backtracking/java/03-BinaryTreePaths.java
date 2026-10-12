/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd append node values to a shared path builder and copy it only at leaves.
 * - Restoring the previous builder length after each call keeps sibling paths independent.
 * Time complexity: O(n + Z) for n nodes and Z total output characters.
 * Space complexity: O(h + P) auxiliary stack/builder for height h and maximum path character
 *   length P, plus O(Z) output.
 */

// 257. Binary Tree Paths
// https://leetcode.com/problems/binary-tree-paths/
// Solution restores its path buffer and does not mutate the tree.
import java.util.*;

class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> out = new ArrayList<>();
        dfs(root, new StringBuilder(), out);
        return out;
    }

    private void dfs(TreeNode node, StringBuilder path, List<String> out) {
        if (node == null) {
            return;
        }
        int before = path.length();
        if (before > 0) {
            path.append("->");
        }
        path.append(node.val);
        if (node.left == null && node.right == null) {
            out.add(path.toString());
        } else {
            dfs(node.left, path, out);
            dfs(node.right, path, out);
        }
        path.setLength(before);
    }
}
