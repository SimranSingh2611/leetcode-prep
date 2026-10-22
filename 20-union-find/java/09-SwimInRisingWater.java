/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd activate cells in increasing elevation and union them with active neighbors.
 * - The first elevation connecting the two endpoints is the minimum water level; unique
 *   elevations allow direct indexing instead of sorting.
 * Time complexity: O(n^2 alpha(n^2)) amortized for an n by n grid.
 * Space complexity: O(n^2) auxiliary position/active/UnionFind storage.
 */

// 778. Swim in Rising Water
// https://leetcode.com/problems/swim-in-rising-water/
// NeetCode 150: https://neetcode.io/problems/swim-in-rising-water/question?list=neetcode150
// Solution preserves grid and relies on the problem's unique bounded elevation range.
import java.util.*;

class Solution {
    public int swimInWater(int[][] grid) {
        int n = grid.length, total = n * n;
        int[] position = new int[total];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                position[grid[r][c]] = r * n + c;
            }
        }
        UnionFind uf = new UnionFind(total);
        boolean[] active = new boolean[total];
        int[] dr = {1, -1, 0, 0}, dc = {0, 0, 1, -1};
        for (int level = 0; level < total; level++) {
            int id = position[level], r = id / n, c = id % n;
            active[id] = true;
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d], nc = c + dc[d];
                if (nr >= 0 && nc >= 0 && nr < n && nc < n && active[nr * n + nc]) {
                    uf.union(id, nr * n + nc);
                }
            }
            if (active[0] && active[total - 1] && uf.isConnected(0, total - 1)) {
                return level;
            }
        }
        throw new IllegalStateException("The full grid must connect");
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
