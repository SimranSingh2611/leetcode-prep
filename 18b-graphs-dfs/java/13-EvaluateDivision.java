// 399. Evaluate Division
// https://leetcode.com/problems/evaluate-division/
// Solution preserves all inputs and uses recursive DFS on the problem's small equation graph.
import java.util.*;

class Solution {
    private static class Edge {
        String to;
        double ratio;

        Edge(String to, double ratio) {
            this.to = to;
            this.ratio = ratio;
        }
    }

    public double[] calcEquation(
            List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, List<Edge>> graph = new HashMap<>();
        for (int i = 0; i < equations.size(); i++) {
            String a = equations.get(i).get(0), b = equations.get(i).get(1);
            graph.computeIfAbsent(a, key -> new ArrayList<>()).add(new Edge(b, values[i]));
            graph.computeIfAbsent(b, key -> new ArrayList<>()).add(new Edge(a, 1.0 / values[i]));
        }
        double[] result = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String a = queries.get(i).get(0), b = queries.get(i).get(1);
            result[i] =
                    !graph.containsKey(a) || !graph.containsKey(b)
                            ? -1.0
                            : dfs(graph, a, b, 1.0, new HashSet<>());
        }
        return result;
    }

    private double dfs(
            Map<String, List<Edge>> graph,
            String node,
            String target,
            double product,
            Set<String> seen) {
        if (node.equals(target)) {
            return product;
        }
        seen.add(node);
        for (Edge edge : graph.get(node)) {
            if (!seen.contains(edge.to)) {
                double answer = dfs(graph, edge.to, target, product * edge.ratio, seen);
                if (answer != -1.0) {
                    return answer;
                }
            }
        }
        return -1.0;
    }
}
