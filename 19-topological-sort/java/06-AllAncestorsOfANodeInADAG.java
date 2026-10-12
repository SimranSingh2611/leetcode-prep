/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd represent ancestor sets with BitSets and propagate them along edges in topological
 *   order.
 * - Each dependent inherits the current node's ancestors and the node itself; increasing set-
 *   bit iteration yields sorted answers.
 * Time complexity: O(V + E + E ceil(V / b) + V^2) worst-case bound including output, with b
 *   bits per machine word.
 * Space complexity: O(V ceil(V / b) + V + E) auxiliary machine words, plus potentially O(V^2)
 *   output entries.
 */

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
