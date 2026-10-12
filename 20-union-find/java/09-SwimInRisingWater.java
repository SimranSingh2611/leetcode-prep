// 778. Swim in Rising Water
// https://leetcode.com/problems/swim-in-rising-water/
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
        DSU dsu = new DSU(total);
        boolean[] active = new boolean[total];
        int[] dr = {1, -1, 0, 0}, dc = {0, 0, 1, -1};
        for (int level = 0; level < total; level++) {
            int id = position[level], r = id / n, c = id % n;
            active[id] = true;
            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d], nc = c + dc[d];
                if (nr >= 0 && nc >= 0 && nr < n && nc < n && active[nr * n + nc]) {
                    dsu.union(id, nr * n + nc);
                }
            }
            if (active[0] && active[total - 1] && dsu.find(0) == dsu.find(total - 1)) {
                return level;
            }
        }
        throw new IllegalStateException("The full grid must connect");
    }

    private static class DSU {
        int[] parent, size;
        int components;

        DSU(int n) {
            parent = new int[n];
            size = new int[n];
            components = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int find(int x) {
            while (x != parent[x]) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        boolean union(int a, int b) {
            a = find(a);
            b = find(b);
            if (a == b) {
                return false;
            }
            if (size[a] < size[b]) {
                int t = a;
                a = b;
                b = t;
            }
            parent[b] = a;
            size[a] += size[b];
            components--;
            return true;
        }
    }
}
