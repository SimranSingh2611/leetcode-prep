/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd serialize nodes in BFS order with explicit markers for missing children.
 * - The decoder consumes child pairs for queued parents, so both shape and values are
 *   reconstructed unambiguously.
 * Time complexity: O(n) per operation for n nodes with fixed-width integer values.
 * Space complexity: Serialization: O(w) queue plus O(n) text/buffer; deserialization: O(n)
 *   tokens and O(w) queue, excluding O(n) reconstructed nodes.
 */

// 297. Serialize and Deserialize Binary Tree
// https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
// NeetCode 150: https://neetcode.io/problems/serialize-and-deserialize-binary-tree/question?list=neetcode150
// Codec design class; supports empty trees and negative values.
import java.util.*;

class Codec {
    public String serialize(TreeNode root) {
        if (root == null) {
            return "#";
        }
        StringBuilder out = new StringBuilder().append(root.val);
        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode node = q.remove();
            if (node.left == null) {
                out.append(",#");
            } else {
                out.append(',').append(node.left.val);
                q.add(node.left);
            }
            if (node.right == null) {
                out.append(",#");
            } else {
                out.append(',').append(node.right.val);
                q.add(node.right);
            }
        }
        return out.toString();
    }

    public TreeNode deserialize(String data) {
        if (data.equals("#")) {
            return null;
        }
        String[] tokens = data.split(",");
        TreeNode root = new TreeNode(Integer.parseInt(tokens[0]));
        Queue<TreeNode> q = new ArrayDeque<>();
        q.add(root);
        int i = 1;
        while (!q.isEmpty()) {
            TreeNode parent = q.remove();
            if (!tokens[i].equals("#")) {
                parent.left = new TreeNode(Integer.parseInt(tokens[i]));
                q.add(parent.left);
            }
            i++;
            if (!tokens[i].equals("#")) {
                parent.right = new TreeNode(Integer.parseInt(tokens[i]));
                q.add(parent.right);
            }
            i++;
        }
        return root;
    }
}
