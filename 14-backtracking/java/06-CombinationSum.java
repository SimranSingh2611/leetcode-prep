/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd choose positive candidates in nondecreasing index order and allow reuse of the chosen
 *   index.
 * - Sorting enables an early stop when a candidate exceeds the remaining target, while index
 *   ordering avoids duplicate combinations.
 * Time complexity: For n candidates, D = floor(target / minCandidate) and output size Z: a
 *   loose bound is O(n log(n + 1) + n^(D + 1) + Z).
 * Space complexity: O(n + D) auxiliary sorted-copy/path/stack, plus O(Z) output.
 */

// 39. Combination Sum
// https://leetcode.com/problems/combination-sum/
// Solution sorts a clone and leaves the caller's candidates unchanged.
import java.util.*;

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        int[] a = candidates.clone();
        Arrays.sort(a);
        List<List<Integer>> out = new ArrayList<>();
        search(a, 0, target, new ArrayList<>(), out);
        return out;
    }

    private void search(
            int[] a, int start, int remaining, List<Integer> path, List<List<Integer>> out) {
        if (remaining == 0) {
            out.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < a.length && a[i] <= remaining; i++) {
            path.add(a[i]);
            search(a, i, remaining - a[i], path, out);
            path.remove(path.size() - 1);
        }
    }
}
