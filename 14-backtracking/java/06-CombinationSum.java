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
