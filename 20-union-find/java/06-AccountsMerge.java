// 721. Accounts Merge
// https://leetcode.com/problems/accounts-merge/
// Solution preserves accounts and returns groups in unspecified order, with each group's emails
// sorted.
import java.util.*;

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        DSU dsu = new DSU(accounts.size());
        Map<String, Integer> owner = new HashMap<>();
        for (int i = 0; i < accounts.size(); i++) {
            List<String> account = accounts.get(i);
            for (int j = 1; j < account.size(); j++) {
                String email = account.get(j);
                Integer earlier = owner.putIfAbsent(email, i);
                if (earlier != null) {
                    dsu.union(i, earlier);
                }
            }
        }
        Map<Integer, TreeSet<String>> groups = new HashMap<>();
        for (Map.Entry<String, Integer> e : owner.entrySet()) {
            groups.computeIfAbsent(dsu.find(e.getValue()), k -> new TreeSet<>()).add(e.getKey());
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
