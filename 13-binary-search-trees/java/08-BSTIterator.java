/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd store the left spine of the remaining inorder traversal.
 * - Each next pops the smallest pending node and pushes its right subtree's left spine; each
 *   node is pushed and popped once.
 * Time complexity: Constructor: O(h); next: O(1) amortized, O(h) worst case; hasNext: O(1).
 * Space complexity: O(h) persistent traversal stack for tree height h.
 */

// 173. Binary Search Tree Iterator
// https://leetcode.com/problems/binary-search-tree-iterator/
// BSTIterator design class; assumes no concurrent tree mutation.
import java.util.*;

class BSTIterator {
    private final Deque<TreeNode> stack = new ArrayDeque<>();

    public BSTIterator(TreeNode root) {
        pushLeft(root);
    }

    private void pushLeft(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

    public int next() {
        TreeNode node = stack.pop();
        pushLeft(node.right);
        return node.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }
}
