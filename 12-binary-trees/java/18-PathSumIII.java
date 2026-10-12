// 437. Path Sum III
// https://leetcode.com/problems/path-sum-iii/
// O(n) expected time, O(h) active prefix map and recursion stack. Input preserved.
import java.util.*;

class Solution {
    public int pathSum(TreeNode root, int targetSum) {
        Map<Long, Integer> prefixes = new HashMap<>();
        prefixes.put(0L, 1); // Empty prefix allows paths starting at the root.
        return count(root, 0L, targetSum, prefixes);
    }

    private int count(TreeNode node, long sum, int target,
                      Map<Long, Integer> prefixes) {
        if (node == null) return 0;
        sum += node.val; // Up to 1000 values of magnitude 10^9 require long.
        int result = prefixes.getOrDefault(sum - target, 0);
        prefixes.put(sum, prefixes.getOrDefault(sum, 0) + 1);
        result += count(node.left, sum, target, prefixes);
        result += count(node.right, sum, target, prefixes);
        // Only ancestors of the current node may remain visible to its sibling.
        int remaining = prefixes.get(sum) - 1;
        if (remaining == 0) prefixes.remove(sum);
        else prefixes.put(sum, remaining);
        return result;
    }
}
