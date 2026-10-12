// 78. Subsets
// https://leetcode.com/problems/subsets/
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
