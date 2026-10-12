// 207. Course Schedule
// https://leetcode.com/problems/course-schedule/
// Solution preserves prerequisites. Pay attention to the pair direction; later Course Schedule
// variants use a different pair convention.
import java.util.*;

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }
        int[] degree = new int[numCourses];
        for (int[] edge : prerequisites) {
            graph.get(edge[1]).add(edge[0]);
            degree[edge[0]]++;
        }
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (degree[i] == 0) {
                queue.offer(i);
            }
        }
        int processed = 0;
        while (!queue.isEmpty()) {
            int u = queue.poll();
            processed++;
            for (int v : graph.get(u)) {
                if (--degree[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        return processed == numCourses;
    }
}
