/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd reject fewer than n - 1 cables, then count connected components with DSU.
 * - Connecting c components needs c - 1 moves, and the edge-count condition guarantees enough
 *   redundant cables.
 * Time complexity: O(n + E alpha(n)) amortized for n computers and E cables.
 * Space complexity: O(n) auxiliary UnionFind arrays.
 */

// 1319. Number of Operations to Make Network Connected
// https://leetcode.com/problems/number-of-operations-to-make-network-connected/
// Solution does not mutate connections.
import java.util.*;

class Solution {
    public int makeConnected(int n, int[][] connections) {
        if (connections.length < n - 1) {
            return -1;
        }
        UnionFind uf = new UnionFind(n);
        for (int[] e : connections) {
            uf.union(e[0], e[1]);
        }
        return uf.numOfComponents() - 1;
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
