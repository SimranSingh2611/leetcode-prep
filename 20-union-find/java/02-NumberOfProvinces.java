/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd scan connected pairs above the symmetric matrix's diagonal and union their cities.
 * - A successful merge reduces the component count; the remaining components are the provinces.
 * Time complexity: O(n^2 alpha(n)) conservative amortized UnionFind bound for n cities.
 * Space complexity: O(n) auxiliary UnionFind arrays; excludes the input matrix.
 */

// 547. Number of Provinces
// https://leetcode.com/problems/number-of-provinces/
// Solution preserves the matrix; a DFS solution is also O(n²) time for this representation.
import java.util.*;

class Solution {
    public int findCircleNum(int[][] isConnected) {
        UnionFind uf = new UnionFind(isConnected.length);
        for (int i = 0; i < isConnected.length; i++) {
            for (int j = i + 1; j < isConnected.length; j++) {
                if (isConnected[i][j] == 1) {
                    uf.union(i, j);
                }
            }
        }
        return uf.numOfComponents();
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
