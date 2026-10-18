/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd scan all vertices and start DFS whenever I find one not yet visited.
 * - Every new traversal identifies one component, including isolated vertices and groups
 *   disconnected from vertex zero.
 * Time complexity: O(V + E) for V vertices and E edges.
 * Space complexity: O(V + E) auxiliary adjacency/visited/stack storage.
 */

// 323. Number of Connected Components in an Undirected Graph
// https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/
// NeetCode 150: https://neetcode.io/problems/count-connected-components/question?list=neetcode150
// Solution preserves edges. Premium NeetCode 150 selection; vertices with no edges still count.
import java.util.*;

class Solution {
    public int countComponents(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        boolean[] seen = new boolean[n];
        Deque<Integer> stack = new ArrayDeque<>();
        int components = 0;
        for (int start = 0; start < n; start++) {
            if (seen[start]) {
                continue;
            }
            components++;
            seen[start] = true;
            stack.push(start);
            while (!stack.isEmpty()) {
                int node = stack.pop();
                for (int next : graph.get(node)) {
                    if (!seen[next]) {
                        seen[next] = true;
                        stack.push(next);
                    }
                }
            }
        }
        return components;
    }
}
