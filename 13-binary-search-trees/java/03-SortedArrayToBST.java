/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd choose the middle array element as the root and recurse on the two index ranges.
 * - Repeated median choices keep the tree balanced without allocating subarray copies.
 * Time complexity: O(n) for n values.
 * Space complexity: O(log(n + 1)) recursive stack, plus O(n) output nodes.
 */

// 108. Convert Sorted Array to Binary Search Tree
// https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
// Solution preserves the input; multiple valid balanced outputs exist.
import java.util.*;

class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    private TreeNode build(int[] a, int l, int r) {
        if (l > r) {
            return null;
        }
        int m = l + (r - l) / 2;
        return new TreeNode(a[m], build(a, l, m - 1), build(a, m + 1, r));
    }
}
