/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd insert sorted products into a trie and cache the first three suggestions at every
 *   prefix.
 * - Then a query follows one path, returning those cached lists for each typed character.
 * Time complexity: O(nL log(n + 1) + S + Q) for n products, maximum product length L, total
 *   product characters S, and query length Q.
 * Space complexity: O(S + n) auxiliary trie/sorted-copy storage, plus O(Q) output references.
 */

// 1268. Search Suggestions System
// https://leetcode.com/problems/search-suggestions-system/
// Solution keeps at most three suggestions per trie node and leaves products unchanged.
import java.util.*;

class Solution {
    private static class TrieNode {
        TrieNode[] child = new TrieNode[26];
        List<String> top = new ArrayList<>(3);
    }

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        String[] sorted = products.clone();
        Arrays.sort(sorted);
        TrieNode root = new TrieNode();
        for (String word : sorted) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                int i = c - 'a';
                if (node.child[i] == null) {
                    node.child[i] = new TrieNode();
                }
                node = node.child[i];
                if (node.top.size() < 3) {
                    node.top.add(word);
                }
            }
        }
        List<List<String>> out = new ArrayList<>();
        TrieNode node = root;
        for (char c : searchWord.toCharArray()) {
            if (node != null) {
                node = node.child[c - 'a'];
            }
            out.add(node == null ? new ArrayList<>() : new ArrayList<>(node.top));
        }
        return out;
    }
}
