// 261. Graph Valid Tree
// https://leetcode.com/problems/graph-valid-tree/
// Solution preserves edges. This Premium NeetCode 150 exercise uses an undirected graph.
import java.util.*;

class Solution {
    public boolean validTree(int n, int[][] edges) {
        if (edges.length != n - 1) {
            return false;
        }
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        boolean[] seen = new boolean[n];
        Queue<Integer> queue = new ArrayDeque<>();
        seen[0] = true;
        queue.offer(0);
        int reached = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            reached++;
            for (int next : graph.get(node)) {
                if (!seen[next]) {
                    seen[next] = true;
                    queue.offer(next);
                }
            }
        }
        return reached == n;
    }
}
