// 2192. All Ancestors of a Node in a Directed Acyclic Graph
// https://leetcode.com/problems/all-ancestors-of-a-node-in-a-directed-acyclic-graph/
// Solution uses Java BitSet for compact set unions and preserves edges.
import java.util.*;

class Solution {
    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        BitSet[] ancestors = new BitSet[n];
        int[] degree = new int[n];
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
            ancestors[i] = new BitSet(n);
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
        while (!queue.isEmpty()) {
            int u = queue.poll();
            for (int v : graph.get(u)) {
                ancestors[v].or(ancestors[u]);
                ancestors[v].set(u);
                if (--degree[v] == 0) {
                    queue.offer(v);
                }
            }
        }
        List<List<Integer>> out = new ArrayList<>();
        for (BitSet set : ancestors) {
            List<Integer> row = new ArrayList<>();
            for (int bit = set.nextSetBit(0); bit >= 0; bit = set.nextSetBit(bit + 1)) {
                row.add(bit);
            }
            out.add(row);
        }
        return out;
    }
}
