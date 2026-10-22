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
        UnionFind alice = new UnionFind(n), bob = new UnionFind(n);
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
        return alice.numOfComponents() == 1 && bob.numOfComponents() == 1 ? edges.length - kept : -1;
    }

    private static class UnionFind {
        private int[] parent;
        private int[] rank;
        private int components;

        UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];

            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }

            components = n;
        }

        public boolean union(int x, int y) {
            int parX = find(x);
            int parY = find(y);

            if (parX == parY) {
                return false;
            }

            if (rank[parX] > rank[parY]) {
                parent[parY] = parX;
            } else if (rank[parX] < rank[parY]) {
                parent[parX] = parY;
            } else {
                parent[parY] = parX;
                rank[parX] += 1;
            }

            components--;
            return true;
        }

        public int find(int x) {
            if (x == parent[x]) {
                return x;
            }

            return parent[x] = find(parent[x]);
        }

        public int numOfComponents() {
            return components;
        }

        public boolean isConnected(int x, int y) {
            return find(x) == find(y);
        }
    }
}
