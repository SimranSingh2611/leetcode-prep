/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd union equivalent letters by rank, then record the smallest letter in each component.
 * - Replacing each base-string character with its component's smallest letter gives the
 *   lexicographically smallest valid result.
 * Time complexity: O(P + B) with the fixed 26-letter alphabet, for P equivalence pairs and base
 *   length B.
 * Space complexity: O(1) auxiliary DSU state, plus O(B) output buffer.
 */

// 1061. Lexicographically Smallest Equivalent String
// https://leetcode.com/problems/lexicographically-smallest-equivalent-string/
// Solution uses the shared UnionFind pattern and tracks each component's smallest letter separately.
import java.util.*;

class Solution {
    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        UnionFind uf = new UnionFind(26);
        for (int i = 0; i < s1.length(); i++) {
            uf.union(s1.charAt(i) - 'a', s2.charAt(i) - 'a');
        }

        // A root chosen by rank is not necessarily the smallest letter in its component.
        int[] smallest = new int[26];
        Arrays.fill(smallest, 26);
        for (int i = 0; i < 26; i++) {
            int root = uf.find(i);
            smallest[root] = Math.min(smallest[root], i);
        }

        StringBuilder out = new StringBuilder();
        for (int i = 0; i < baseStr.length(); i++) {
            int root = uf.find(baseStr.charAt(i) - 'a');
            out.append((char) ('a' + smallest[root]));
        }
        return out.toString();
    }

    private static class UnionFind {
        private int[] parent;
        private int[] rank;
        private int components;

        UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }

            components = n;
        }

        public boolean union(int x, int y) {
            int parX = find(x);
            int parY = find(y);

            if (parX == parY) {
                return false;
            }

            if (rank[parX] > rank[parY]) {
                parent[parY] = parX;
            } else if (rank[parX] < rank[parY]) {
                parent[parX] = parY;
            } else {
                parent[parY] = parX;
                rank[parX] += 1;
            }

            components--;
            return true;
        }

        public int find(int x) {
            if (x == parent[x]) {
                return x;
            }

            return parent[x] = find(parent[x]);
        }

        public int numOfComponents() {
            return components;
        }

        public boolean isConnected(int x, int y) {
            return find(x) == find(y);
        }
    }
}
