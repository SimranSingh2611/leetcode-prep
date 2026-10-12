// 1462. Course Schedule IV
// https://leetcode.com/problems/course-schedule-iv/
// Solution assumes the problem's acyclic graph guarantee. Its pair direction differs from Course
// Schedule I/II.
import java.util.*;

class Solution {
    public List<Boolean> checkIfPrerequisite(
            int numCourses, int[][] prerequisites, int[][] queries) {
        int n = numCourses;
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        int[] degree = new int[n];
        boolean[][] reach = new boolean[n][n];
        for (int[] e : prerequisites) {
            graph.get(e[0]).add(e[1]);
            degree[e[1]]++;
        }
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 0) {
                queue.offer(i);
            }
        }
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v : graph.get(u)) {
                reach[u][v] = true;
                for (int p = 0; p < n; p++) {
                    if (reach[p][u]) {
                        reach[p][v] = true;
                    }
                }
                if (--degree[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        List<Boolean> out = new ArrayList<>();
        for (int[] q : queries) {
            out.add(reach[q[0]][q[1]]);
        }
        return out;
    }
}
