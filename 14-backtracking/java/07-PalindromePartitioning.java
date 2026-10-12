/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd precompute which substrings are palindromes, then backtrack over valid next cuts.
 * - Reaching the end records one partition, and the table makes each proposed cut's palindrome
 *   check constant time.
 * Time complexity: O(n^2 + n 2^n) including copying for string length n.
 * Space complexity: O(n^2) auxiliary palindrome table and path/stack, plus up to O(n 2^n)
 *   output.
 */

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
