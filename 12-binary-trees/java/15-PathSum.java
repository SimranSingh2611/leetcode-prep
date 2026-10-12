/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd subtract each visited value from the remaining target.
 * - I'd accept only at a leaf when that remainder is satisfied, so an internal prefix cannot
 *   produce a false match.
 * Time complexity: O(n) for n nodes.
 * Space complexity: O(h) recursive stack for height h.
 */

// 112. Path Sum
// https://leetcode.com/problems/path-sum/
// O(n) time, O(h) recursion stack. Input preserved.
class Solution {
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) return targetSum == root.val;
        int remaining = targetSum - root.val;
        return hasPathSum(root.left, remaining) || hasPathSum(root.right, remaining);
    }
}
