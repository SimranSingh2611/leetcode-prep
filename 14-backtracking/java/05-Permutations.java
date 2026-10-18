/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd choose an unused index at each depth and mark it until that recursive branch returns.
 * - A length-n path is one permutation; undoing both the mark and append enables the next
 *   choice.
 * Time complexity: O(n n!) including permutation copies for n values.
 * Space complexity: O(n) auxiliary used-array/path/stack, plus O(n n!) output.
 */

// 46. Permutations
// https://leetcode.com/problems/permutations/
// NeetCode 150: https://neetcode.io/problems/permutations/question?list=neetcode150
// Solution uses a used-index array and does not mutate nums.
import java.util.*;

class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> out = new ArrayList<>();
        search(nums, new boolean[nums.length], new ArrayList<>(), out);
        return out;
    }

    private void search(int[] a, boolean[] used, List<Integer> path, List<List<Integer>> out) {
        if (path.size() == a.length) {
            out.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < a.length; i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            path.add(a[i]);
            search(a, used, path, out);
            path.remove(path.size() - 1);
            used[i] = false;
        }
    }
}
