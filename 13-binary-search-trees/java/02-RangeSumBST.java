/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd prune the left subtree when the current value is below the range, and the right subtree
 *   when it is above.
 * - Values inside the range contribute to the sum and require exploring both sides.
 * Time complexity: O(n) worst case for n nodes; pruning can reduce visits.
 * Space complexity: O(h) recursive stack for height h.
 */

// 938. Range Sum of BST
// https://leetcode.com/problems/range-sum-of-bst/
// Solution; no mutation.
import java.util.*;

class Solution {
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }
        if (root.val < low) {
            return rangeSumBST(root.right, low, high);
        }
        if (root.val > high) {
            return rangeSumBST(root.left, low, high);
        }
        return root.val + rangeSumBST(root.left, low, high) + rangeSumBST(root.right, low, high);
    }
}
