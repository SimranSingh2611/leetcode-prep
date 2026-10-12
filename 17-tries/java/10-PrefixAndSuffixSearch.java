// 745. Prefix and Suffix Search
// https://leetcode.com/problems/prefix-and-suffix-search/
// WordFilter is the design class. The 27th child is the separator, and later duplicate words
// correctly overwrite earlier indices.
import java.util.*;

class WordFilter {
    private static class TrieNode {
        TrieNode[] child = new TrieNode[27];
        int index = -1;
    }

    private final TrieNode root = new TrieNode();

    public WordFilter(String[] words) {
        for (int index = 0; index < words.length; index++) {
            String word = words[index];
            for (int start = 0; start <= word.length(); start++) {
                String key = word.substring(start) + "{" + word;
                TrieNode node = root;
                for (int i = 0; i < key.length(); i++) {
                    int c = key.charAt(i) - 'a';
                    if (node.child[c] == null) {
                        node.child[c] = new TrieNode();
                    }
                    node = node.child[c];
                    node.index = index;
                }
            }
        }
    }

    public int f(String pref, String suff) {
        String key = suff + "{" + pref;
        TrieNode node = root;
        for (int i = 0; i < key.length(); i++) {
            node = node.child[key.charAt(i) - 'a'];
            if (node == null) {
                return -1;
            }
        }
        return node.index;
    }
}
