/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - Preorder picks the root, and its inorder index divides the left and right subtrees.
 * - I'd map values to inorder positions and recurse on index ranges, avoiding repeated searches
 *   or copied slices.
 * Time complexity: O(n) expected for n nodes.
 * Space complexity: O(n) auxiliary index map plus O(h) stack, excluding O(n) output nodes.
 */

// 105. Construct Binary Tree from Preorder and Inorder Traversal
// https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
// NeetCode 150: https://neetcode.io/problems/binary-tree-from-preorder-and-inorder-traversal/question?list=neetcode150
// Solution; inputs preserved.
import java.util.*;

class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> index = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            index.put(inorder[i], i);
        }
        return build(preorder, 0, 0, inorder.length - 1, index);
    }

    private TreeNode build(int[] p, int start, int left, int right, Map<Integer, Integer> index) {
        if (left > right) {
            return null;
        }
        TreeNode root = new TreeNode(p[start]);
        int split = index.get(root.val);
        root.left = build(p, start + 1, left, split - 1, index);
        root.right = build(p, start + 1 + split - left, split + 1, right, index);
        return root;
    }
}
