/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd scan connected pairs above the symmetric matrix's diagonal and union their cities.
 * - A successful merge reduces the component count; the remaining components are the provinces.
 * Time complexity: O(n^2 alpha(n)) conservative amortized DSU bound for n cities.
 * Space complexity: O(n) auxiliary DSU arrays; excludes the input matrix.
 */

// 547. Number of Provinces
// https://leetcode.com/problems/number-of-provinces/
// Solution preserves the matrix; a DFS solution is also O(n²) time for this representation.
import java.util.*;

class Solution {
    public int findCircleNum(int[][] isConnected) {
        DSU dsu = new DSU(isConnected.length);
        for (int i = 0; i < isConnected.length; i++) {
            for (int j = i + 1; j < isConnected.length; j++) {
                if (isConnected[i][j] == 1) {
                    dsu.union(i, j);
                }
            }
        }
        return dsu.components;
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
