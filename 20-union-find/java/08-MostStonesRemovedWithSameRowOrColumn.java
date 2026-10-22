/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd union stones sharing a row or column using maps to one representative per coordinate.
 * - Each connected group can lose all but one stone, so the answer is the stone count minus the
 *   component count.
 * Time complexity: O(n alpha(n)) expected amortized for n stones.
 * Space complexity: O(n) auxiliary UnionFind and row/column maps.
 */

// 947. Most Stones Removed with Same Row or Column
// https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/
// Solution keeps row and column maps separate and preserves stones.
import java.util.*;

class Solution {
    public int removeStones(int[][] stones) {
        UnionFind uf = new UnionFind(stones.length);
        Map<Integer, Integer> rows = new HashMap<>(), cols = new HashMap<>();
        for (int i = 0; i < stones.length; i++) {
            Integer row = rows.putIfAbsent(stones[i][0], i),
                    col = cols.putIfAbsent(stones[i][1], i);
            if (row != null) {
                uf.union(i, row);
            }
            if (col != null) {
                uf.union(i, col);
            }
        }
        return stones.length - uf.numOfComponents();
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
