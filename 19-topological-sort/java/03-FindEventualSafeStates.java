/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd reverse the edges and begin with terminal nodes, which are immediately safe.
 * - A predecessor becomes safe only after all its outgoing neighbors do, leaving cycle-reaching
 *   nodes unresolved.
 * Time complexity: O(V + E) for V vertices and E edges.
 * Space complexity: O(V + E) auxiliary reversed graph/counts/queue, plus up to O(V) output.
 */

// 802. Find Eventual Safe States
// https://leetcode.com/problems/find-eventual-safe-states/
// Solution preserves graph and returns safe node indices in ascending order.
import java.util.*;

class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        List<List<Integer>> reverse = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            reverse.add(new ArrayList<>());
        }
        int[] outgoing = new int[n];
        for (int u = 0; u < n; u++) {
            outgoing[u] = graph[u].length;
            for (int v : graph[u]) {
                reverse.get(v).add(u);
            }
        }
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (outgoing[i] == 0) {
                queue.offer(i);
            }
        }
        boolean[] safe = new boolean[n];
        while (!queue.isEmpty()) {
            int u = queue.poll();
            safe[u] = true;
            for (int p : reverse.get(u)) {
                if (--outgoing[p] == 0) {
                    queue.offer(p);
                }
            }
        }
        List<Integer> out = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (safe[i]) {
                out.add(i);
            }
        }
        return out;
    }
}
