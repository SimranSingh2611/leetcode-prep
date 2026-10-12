/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd process shared edges first in separate DSUs for Alice and Bob.
 * - A successful shared merge benefits both users; after their private edges, unused edges are
 *   removable if both graphs are connected.
 * Time complexity: O(n + E alpha(n)) amortized for n vertices and E edges.
 * Space complexity: O(n) auxiliary storage for the two DSUs.
 */

// 1579. Remove Max Number of Edges to Keep Graph Fully Traversable
// https://leetcode.com/problems/remove-max-number-of-edges-to-keep-graph-fully-traversable/
// Solution preserves edges and always executes both shared-edge unions; it does not hide the second
// union behind short-circuit OR.
import java.util.*;

class Solution {
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        DSU alice = new DSU(n), bob = new DSU(n);
        int kept = 0;
        for (int[] e : edges) {
            if (e[0] == 3) {
                boolean a = alice.union(e[1] - 1, e[2] - 1);
                boolean b = bob.union(e[1] - 1, e[2] - 1);
                if (a || b) {
                    kept++;
                }
            }
        }
        for (int[] e : edges) {
            if (e[0] == 1 && alice.union(e[1] - 1, e[2] - 1)) {
                kept++;
            } else if (e[0] == 2 && bob.union(e[1] - 1, e[2] - 1)) {
                kept++;
            }
        }
        return alice.components == 1 && bob.components == 1 ? edges.length - kept : -1;
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
