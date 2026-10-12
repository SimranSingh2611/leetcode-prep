// 2392. Build a Matrix With Conditions
// https://leetcode.com/problems/build-a-matrix-with-conditions/
// Solution preserves conditions and returns any valid matrix; an empty matrix signals
// infeasibility.
import java.util.*;

class Solution {
    public int[][] buildMatrix(int k, int[][] rowConditions, int[][] colConditions) {
        int[] row = order(k, rowConditions), col = order(k, colConditions);
        if (row.length == 0 || col.length == 0) {
            return new int[0][0];
        }
        int[] column = new int[k + 1];
        for (int i = 0; i < k; i++) {
            column[col[i]] = i;
        }
        int[][] out = new int[k][k];
        for (int i = 0; i < k; i++) {
            out[i][column[row[i]]] = row[i];
        }
        return out;
    }

    private int[] order(int k, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        int[] degree = new int[k + 1];
        for (int i = 0; i <= k; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] e : edges) {
            graph.get(e[0]).add(e[1]);
            degree[e[1]]++;
        }
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= k; i++) {
            if (degree[i] == 0) {
                queue.offer(i);
            }
        }
        int[] out = new int[k];
        int used = 0;
        while (!queue.isEmpty()) {
            int u = queue.poll();
            out[used++] = u;
            for (int v : graph.get(u)) {
                if (--degree[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        return used == k ? out : new int[0];
    }
}
