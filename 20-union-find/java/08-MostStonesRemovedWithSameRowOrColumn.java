// 947. Most Stones Removed with Same Row or Column
// https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/
// Solution keeps row and column maps separate and preserves stones.
import java.util.*;

class Solution {
    public int removeStones(int[][] stones) {
        DSU dsu = new DSU(stones.length);
        Map<Integer, Integer> rows = new HashMap<>(), cols = new HashMap<>();
        for (int i = 0; i < stones.length; i++) {
            Integer row = rows.putIfAbsent(stones[i][0], i),
                    col = cols.putIfAbsent(stones[i][1], i);
            if (row != null) {
                dsu.union(i, row);
            }
            if (col != null) {
                dsu.union(i, col);
            }
        }
        return stones.length - dsu.components;
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
