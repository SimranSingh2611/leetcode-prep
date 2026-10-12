// 677. Map Sum Pairs
// https://leetcode.com/problems/map-sum-pairs/
// MapSum is the design class. Stored sums use int under the problem's bounds.
import java.util.*;

class MapSum {
    private static class TrieNode {
        TrieNode[] child = new TrieNode[26];
        int sum;
    }

    private final TrieNode root = new TrieNode();
    private final Map<String, Integer> values = new HashMap<>();

    public MapSum() {}

    public void insert(String key, int val) {
        int delta = val - values.getOrDefault(key, 0);
        values.put(key, val);
        TrieNode node = root;
        node.sum += delta;
        for (char c : key.toCharArray()) {
            int i = c - 'a';
            if (node.child[i] == null) {
                node.child[i] = new TrieNode();
            }
            node = node.child[i];
            node.sum += delta;
        }
    }

    public int sum(String prefix) {
        TrieNode node = root;
        for (char c : prefix.toCharArray()) {
            node = node.child[c - 'a'];
            if (node == null) {
                return 0;
            }
        }
        return node.sum;
    }
}
