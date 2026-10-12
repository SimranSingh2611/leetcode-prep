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
