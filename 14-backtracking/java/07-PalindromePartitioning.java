// 131. Palindrome Partitioning
// https://leetcode.com/problems/palindrome-partitioning/
// Solution uses a precomputed palindrome table; substring objects are copied into saved partitions.
import java.util.*;

class Solution {
    public List<List<String>> partition(String s) {
        int n = s.length();
        boolean[][] pal = new boolean[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                pal[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 2 || pal[i + 1][j - 1]);
            }
        }
        List<List<String>> out = new ArrayList<>();
        search(s, 0, pal, new ArrayList<>(), out);
        return out;
    }

    private void search(
            String s, int start, boolean[][] pal, List<String> path, List<List<String>> out) {
        if (start == s.length()) {
            out.add(new ArrayList<>(path));
            return;
        }
        for (int end = start; end < s.length(); end++) {
            if (!pal[start][end]) {
                continue;
            }
            path.add(s.substring(start, end + 1));
            search(s, end + 1, pal, path, out);
            path.remove(path.size() - 1);
        }
    }
}
