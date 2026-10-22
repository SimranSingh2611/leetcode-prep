/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd first union all equalities to establish the complete equivalence classes.
 * - Then every inequality must connect different representatives; checking too early could miss
 *   a later equality chain.
 * Time complexity: O(E alpha(26) + 26) = O(E + 1) for E equations over fixed lowercase
 *   variables.
 * Space complexity: O(26) = O(1) auxiliary UnionFind space.
 */

// 990. Satisfiability of Equality Equations
// https://leetcode.com/problems/satisfiability-of-equality-equations/
// Solution performs two passes and leaves equations unchanged.
import java.util.*;

class Solution {
    public boolean equationsPossible(String[] equations) {
        UnionFind uf = new UnionFind(26);
        for (String e : equations) {
            if (e.charAt(1) == '=') {
                uf.union(e.charAt(0) - 'a', e.charAt(3) - 'a');
            }
        }
        for (String e : equations) {
            if (e.charAt(1) == '!' && uf.isConnected(e.charAt(0) - 'a', e.charAt(3) - 'a')) {
                return false;
            }
        }
        return true;
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
