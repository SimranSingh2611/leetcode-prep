/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd store a prefix sum at every trie node and remember each full key's old value.
 * - Updating a key adds only the value difference along its path, so overwrites do not double
 *   count.
 * Time complexity: Expected O(L) per insert for key length L; O(P) per sum for prefix length P.
 * Space complexity: O(S) persistent trie/key-map storage for total distinct-key characters S.
 */

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
