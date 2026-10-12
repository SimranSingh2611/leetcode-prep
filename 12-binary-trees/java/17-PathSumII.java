/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd carry a mutable root-to-node path and the remaining target.
 * - At a matching leaf I'd save a copy, then undo each append on return so sibling paths and
 *   saved answers stay independent.
 * Time complexity: O(n + L) for n nodes and L total values copied into answers; L can be
 *   O(n^2).
 * Space complexity: O(h) auxiliary path/stack, plus O(L) output.
 */

// 113. Path Sum II
// https://leetcode.com/problems/path-sum-ii/
// O(n + L) time; O(h) auxiliary space plus O(L) output for L emitted values.
// Input preserved. Copy each matching path before backtracking.
import java.util.*;

class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        collect(root, targetSum, new ArrayList<>(), result);
        return result;
    }

    private void collect(TreeNode node, int remaining, List<Integer> path,
                         List<List<Integer>> result) {
        if (node == null) return;
        path.add(node.val);
        remaining -= node.val;
        if (node.left == null && node.right == null && remaining == 0) {
            result.add(new ArrayList<>(path));
        }
        collect(node.left, remaining, path, result);
        collect(node.right, remaining, path, result);
        path.remove(path.size() - 1);
    }
}
