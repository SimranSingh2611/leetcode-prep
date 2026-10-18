/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd direct each prerequisite edge toward its dependent course and queue courses with zero
 *   indegree.
 * - Removing ready courses unlocks others; processing fewer than all courses reveals a cycle.
 * Time complexity: O(V + E) for V courses and E prerequisite edges.
 * Space complexity: O(V + E) auxiliary adjacency/indegree/queue storage.
 */

// 207. Course Schedule
// https://leetcode.com/problems/course-schedule/
// NeetCode 150: https://neetcode.io/problems/course-schedule/question?list=neetcode150
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
