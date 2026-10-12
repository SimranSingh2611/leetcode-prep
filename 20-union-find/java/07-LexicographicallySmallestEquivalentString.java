/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd union equivalent letters while always choosing the smallest letter as representative.
 * - Replacing each base-string character with its representative gives the lexicographically
 *   smallest valid result.
 * Time complexity: O(P + B) with the fixed 26-letter alphabet, for P equivalence pairs and base
 *   length B.
 * Space complexity: O(1) auxiliary DSU state, plus O(B) output buffer.
 */

// 1061. Lexicographically Smallest Equivalent String
// https://leetcode.com/problems/lexicographically-smallest-equivalent-string/
// Solution deliberately chooses the minimum root instead of union by size. For large universes, use
// size/rank plus separate minimum metadata.
import java.util.*;

class Solution {
    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        int[] parent = new int[26];
        for (int i = 0; i < 26; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < s1.length(); i++) {
            int a = find(parent, s1.charAt(i) - 'a'), b = find(parent, s2.charAt(i) - 'a');
            parent[Math.max(a, b)] = Math.min(a, b);
        }
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < baseStr.length(); i++) {
            out.append((char) ('a' + find(parent, baseStr.charAt(i) - 'a')));
        }
        return out.toString();
    }

    private int find(int[] parent, int x) {
        while (x != parent[x]) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }
}
