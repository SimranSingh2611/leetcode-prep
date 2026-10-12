/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd backtrack through each outgoing edge while carrying the current source-to-node path.
 * - I'd copy the path at the target and avoid a global visited set, since different paths may
 *   share later vertices.
 * Time complexity: O(n 2^n) worst-case bound including path copies for an n-vertex DAG.
 * Space complexity: O(n) auxiliary path/stack, plus potentially O(n 2^n) output.
 */

// 797. All Paths From Source to Target
// https://leetcode.com/problems/all-paths-from-source-to-target/
// Solution preserves graph and copies each output path. This is DFS plus backtracking,
// intentionally connecting 14 to graph traversal.
import java.util.*;

class Solution {
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        path.add(0);
        dfs(graph, 0, path, result);
        return result;
    }

    private void dfs(int[][] graph, int node, List<Integer> path, List<List<Integer>> result) {
        if (node == graph.length - 1) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int next : graph[node]) {
            path.add(next);
            dfs(graph, next, path, result);
            path.remove(path.size() - 1);
        }
    }
}
