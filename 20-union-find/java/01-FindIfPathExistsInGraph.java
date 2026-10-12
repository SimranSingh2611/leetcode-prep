/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd union the endpoints of every undirected edge.
 * - A path exists exactly when the source and destination have the same final representative.
 * Time complexity: O(n + E alpha(n)) amortized for n vertices and E edges; alpha is the inverse
 *   Ackermann function.
 * Space complexity: O(n) auxiliary DSU arrays.
 */

// 1971. Find if Path Exists in Graph
// https://leetcode.com/problems/find-if-path-exists-in-graph/
// Solution uses iterative path halving and union by size; inputs are unchanged.
import java.util.*;

class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        DSU dsu = new DSU(n);
        for (int[] e : edges) {
            dsu.union(e[0], e[1]);
        }
        return dsu.find(source) == dsu.find(destination);
    }

    private static class DSU {
        int[] parent, size;
        int components;

        DSU(int n) {
            parent = new int[n];
            size = new int[n];
            components = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        int find(int x) {
            while (x != parent[x]) {
                parent[x] = parent[parent[x]];
                x = parent[x];
            }
            return x;
        }

        boolean union(int a, int b) {
            a = find(a);
            b = find(b);
            if (a == b) {
                return false;
            }
            if (size[a] < size[b]) {
                int t = a;
                a = b;
                b = t;
            }
            parent[b] = a;
            size[a] += size[b];
            components--;
            return true;
        }
    }
}
