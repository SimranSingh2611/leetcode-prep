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
