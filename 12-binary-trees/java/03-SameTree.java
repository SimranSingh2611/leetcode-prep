/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd compare corresponding nodes, requiring both their values and structures to match.
 * - Two nulls match, one null fails, and matching nodes recurse on both child pairs.
 * Time complexity: O(n) worst case for n compared node positions.
 * Space complexity: O(h) recursive stack for the maximum compared depth.
 */

// 100. Same Tree
// https://leetcode.com/problems/same-tree/
// NeetCode 150: https://neetcode.io/problems/same-binary-tree/question?list=neetcode150
// Solution; input preserved.
import java.util.*;

class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null || q == null) {
            return p == q;
        }
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
