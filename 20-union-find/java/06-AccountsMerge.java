/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd map each email to its first account and union accounts sharing that email.
 * - Grouping unique emails by final representative reconstructs each person, and sorted sets
 *   supply the required email order.
 * Time complexity: Expected O(A + T alpha(A) + U log(U + 1)) for accounts A, email occurrences
 *   T, and unique emails U, treating names/emails as bounded-length keys.
 * Space complexity: O(A + U) auxiliary DSU/maps/sets, excluding output; string character costs
 *   apply when lengths are unbounded.
 */

// 721. Accounts Merge
// https://leetcode.com/problems/accounts-merge/
// Solution preserves accounts and returns groups in unspecified order, with each group's emails
// sorted.
import java.util.*;

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        UnionFind uf = new UnionFind(accounts.size());
        Map<String, Integer> owner = new HashMap<>();
        for (int i = 0; i < accounts.size(); i++) {
            List<String> account = accounts.get(i);
            for (int j = 1; j < account.size(); j++) {
                String email = account.get(j);
                Integer earlier = owner.putIfAbsent(email, i);
                if (earlier != null) {
                    uf.union(i, earlier);
                }
            }
        }
        Map<Integer, TreeSet<String>> groups = new HashMap<>();
        for (Map.Entry<String, Integer> e : owner.entrySet()) {
            groups.computeIfAbsent(uf.find(e.getValue()), k -> new TreeSet<>()).add(e.getKey());
        }
        List<List<String>> out = new ArrayList<>();
        for (Map.Entry<Integer, TreeSet<String>> e : groups.entrySet()) {
            List<String> account = new ArrayList<>();
            account.add(accounts.get(e.getKey()).get(0));
            account.addAll(e.getValue());
            out.add(account);
        }
        return out;
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
