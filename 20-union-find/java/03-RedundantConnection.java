/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd process edges in their input order and union endpoints that are not already connected.
 * - The first failed union closes the unique cycle and identifies the required redundant edge
 *   under the tree-plus-one-edge guarantee.
 * Time complexity: O(n alpha(n)) amortized for n edges/vertices.
 * Space complexity: O(n) auxiliary UnionFind arrays.
 */

// 684. Redundant Connection
// https://leetcode.com/problems/redundant-connection/
// NeetCode 150: https://neetcode.io/problems/redundant-connection/question?list=neetcode150
// Solution returns a cloned edge and preserves input; labels are 1-based.
import java.util.*;

class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        UnionFind uf = new UnionFind(edges.length + 1);
        for (int[] e : edges) {
            if (!uf.union(e[0], e[1])) {
                return e.clone();
            }
        }
        return new int[0];
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
