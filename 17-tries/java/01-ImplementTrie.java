/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd store one trie edge per lowercase character and mark complete word endings.
 * - Exact search requires a terminal marker, while prefix search succeeds as soon as the whole
 *   prefix path exists.
 * Time complexity: Each insert, search, or startsWith: O(L) for argument length L.
 * Space complexity: O(S) persistent trie space for total inserted characters S; O(1) traversal
 *   state per operation.
 */

// 208. Implement Trie (Prefix Tree)
// https://leetcode.com/problems/implement-trie-prefix-tree/
// NeetCode 150: https://neetcode.io/problems/implement-prefix-tree/question?list=neetcode150
// Trie is the required design class. The implementation assumes lowercase English letters.
import java.util.*;

class Trie {
    private static class TrieNode {
        TrieNode[] child = new TrieNode[26];
        boolean end;
    }

    private final TrieNode root = new TrieNode();

    public Trie() {}

    public void insert(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            int c = word.charAt(i) - 'a';
            if (node.child[c] == null) {
                node.child[c] = new TrieNode();
            }
            node = node.child[c];
        }
        node.end = true;
    }

    public boolean search(String word) {
        TrieNode node = walk(word);
        return node != null && node.end;
    }

    public boolean startsWith(String prefix) {
        return walk(prefix) != null;
    }

    private TrieNode walk(String s) {
        TrieNode node = root;
        for (int i = 0; i < s.length(); i++) {
            node = node.child[s.charAt(i) - 'a'];
            if (node == null) {
                return null;
            }
        }
        return node;
    }
}
