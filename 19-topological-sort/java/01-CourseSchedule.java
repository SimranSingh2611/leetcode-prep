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
        // Topological sort
        Map<Integer, Integer> indegree = new HashMap<>();
        Map<Integer, List<Integer>> adj = new HashMap<>();

        for(int i=0; i<numCourses; i++) {
            indegree.put(i, 0);
            adj.put(i , new ArrayList<Integer>());
        }


        // prereq -> course
        for(int[] pair : prerequisites) {
            int course = pair[0];
            int prereq = pair[1];

            indegree.put(course, indegree.getOrDefault(course, 0)+1);
            adj.get(prereq).add(course);
        }


        Queue<Integer> q = new LinkedList<>();
         // Step 2. Add nodes with 0 indegree to the queue.
        for(int i=0; i<numCourses; i++) {
            if (indegree.get(i) == 0)
                q.add(i);
        }

        int nodesVisited = 0;
        // Step 3. Traverse the queue and reduce indegree by 1 for each of its children. When indegree reaches 0, add it to the q. 
        // NOTE: Whenever the indegree is 0, always add it to the q. 
        while(!q.isEmpty()) {
            int node = q.poll();
            nodesVisited++;

            for(int neighbor : adj.get(node)) {
                indegree.put(neighbor, indegree.get(neighbor)-1);

                if (indegree.get(neighbor) == 0)
                    q.add(neighbor);
            }
        }

        return nodesVisited == numCourses;
    }
}