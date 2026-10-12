/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd keep the best count of each color for paths ending at every vertex.
 * - Topological propagation updates those 26 counts, and an incomplete processed count detects
 *   a cycle anywhere in the graph.
 * Time complexity: O(26(V + E)) = O(V + E) for a fixed 26-color alphabet.
 * Space complexity: O(26V + E) = O(V + E) auxiliary graph/color-table/queue space.
 */

// 1857. Largest Color Value in a Directed Graph
// https://leetcode.com/problems/largest-color-value-in-a-directed-graph/
// Solution is a topological-sort + vector-DP stretch problem. Each color's best count may come from
// a different path; the vector is not one shared physical path.
import java.util.*;

class Solution {
    public int largestPathValue(String colors, int[][] edges) {
        int n = colors.length();
        List<List<Integer>> graph = new ArrayList<>();
        int[] degree = new int[n];
        int[][] best = new int[n][26];
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
            best[i][colors.charAt(i) - 'a'] = 1;
        }
        for (int[] e : edges) {
            graph.get(e[0]).add(e[1]);
            degree[e[1]]++;
        }
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (degree[i] == 0) {
                queue.offer(i);
            }
        }
        int answer = 0, processed = 0;
        while (!queue.isEmpty()) {
            int u = queue.poll();
            processed++;
            for (int count : best[u]) {
                answer = Math.max(answer, count);
            }
            for (int v : graph.get(u)) {
                int color = colors.charAt(v) - 'a';
                for (int c = 0; c < 26; c++) {
                    best[v][c] = Math.max(best[v][c], best[u][c] + (c == color ? 1 : 0));
                }
                if (--degree[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        return processed == n ? answer : -1;
    }
}
