/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd track each course's earliest finish while processing prerequisites topologically.
 * - A dependent finishes after its latest-finishing prerequisite plus its own duration;
 *   independent courses can overlap.
 * Time complexity: O(V + E) for V courses and E edges.
 * Space complexity: O(V + E) auxiliary graph/finish/queue storage.
 */

// 2050. Parallel Courses III
// https://leetcode.com/problems/parallel-courses-iii/
// Solution normalizes the problem's 1-based course labels. This is topological ordering plus a
// one-number-per-node DP; inputs are unchanged.
import java.util.*;

class Solution {
    public int minimumTime(int n, int[][] relations, int[] time) {
        List<List<Integer>> graph = new ArrayList<>();
        int[] degree = new int[n], finish = time.clone();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] e : relations) {
            graph.get(e[0] - 1).add(e[1] - 1);
            degree[e[1] - 1]++;
        }
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 0) {
                queue.offer(i);
            }
        }
        int best = 0;
        while (!queue.isEmpty()) {
            int u = queue.poll();
            best = Math.max(best, finish[u]);
            for (int v : graph.get(u)) {
                finish[v] = Math.max(finish[v], finish[u] + time[v]);
                if (--degree[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        return best;
    }
}
