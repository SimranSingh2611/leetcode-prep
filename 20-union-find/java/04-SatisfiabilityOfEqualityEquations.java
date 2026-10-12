// 990. Satisfiability of Equality Equations
// https://leetcode.com/problems/satisfiability-of-equality-equations/
// Solution performs two passes and leaves equations unchanged.
import java.util.*;

class Solution {
    public boolean equationsPossible(String[] equations) {
        DSU dsu = new DSU(26);
        for (String e : equations) {
            if (e.charAt(1) == '=') {
                dsu.union(e.charAt(0) - 'a', e.charAt(3) - 'a');
            }
        }
        for (String e : equations) {
            if (e.charAt(1) == '!' && dsu.find(e.charAt(0) - 'a') == dsu.find(e.charAt(3) - 'a')) {
                return false;
            }
        }
        return true;
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
