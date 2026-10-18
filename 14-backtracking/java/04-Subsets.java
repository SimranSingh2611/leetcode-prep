/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd record the current subset, then choose only later indices for its next element.
 * - Undoing each append restores the path, and copying when recording prevents later mutations
 *   from changing answers.
 * Time complexity: O(n 2^n) including subset copies for n values.
 * Space complexity: O(n) auxiliary stack/path, plus O(n 2^n) output.
 */

// 78. Subsets
// https://leetcode.com/problems/subsets/
// NeetCode 150: https://neetcode.io/problems/subsets/question?list=neetcode150
// Solution leaves nums unchanged and saves independent list copies.
import java.util.*;

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> out = new ArrayList<>();
        search(nums, 0, new ArrayList<>(), out);
        return out;
    }

    private void search(int[] a, int start, List<Integer> path, List<List<Integer>> out) {
        out.add(new ArrayList<>(path));
        for (int i = start; i < a.length; i++) {
            path.add(a[i]);
            search(a, i + 1, path, out);
            path.remove(path.size() - 1);
        }
    }
}
