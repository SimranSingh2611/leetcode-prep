// 210. Course Schedule II
// https://leetcode.com/problems/course-schedule-ii/
// Solution returns any valid order and leaves input unchanged.
import java.util.*;

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
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
        int[] order = new int[numCourses];
        int used = 0;
        while (!queue.isEmpty()) {
            int u = queue.poll();
            order[used++] = u;
            for (int v : graph.get(u)) {
                if (--degree[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        return used == numCourses ? order : new int[0];
    }
}
