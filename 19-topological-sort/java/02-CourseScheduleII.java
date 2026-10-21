/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd run Kahn's algorithm and append each ready course to the result.
 * - Every prerequisite has already been emitted when a course becomes ready; an incomplete
 *   order means a cycle.
 * Time complexity: O(V + E) for V courses and E edges.
 * Space complexity: O(V + E) auxiliary graph/queue storage, plus O(V) output.
 */

// 210. Course Schedule II
// https://leetcode.com/problems/course-schedule-ii/
// NeetCode 150: https://neetcode.io/problems/course-schedule-ii/question?list=neetcode150
// Solution returns any valid order and leaves input unchanged.
import java.util.*;

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
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
        // Step 3. Traverse the queue and reduce indegree by 1 for each of its children. When indegree reaches 0, add it to the q. 
        // NOTE: Whenever the indegree is 0, always add it to the q. 
        int[] out = new int[numCourses];
        int index = 0;
        while(!q.isEmpty()) {
            int node = q.poll();
            
            out[index] = node;
            index++;

            for(int neighbor : adj.get(node)) {
                indegree.put(neighbor, indegree.get(neighbor)-1);

                if (indegree.get(neighbor) == 0)
                    q.add(neighbor);
            }
        }

        // For cycle detection.
        if (index < numCourses)
            return new int[]{};
        
        return out;
    }
it c}