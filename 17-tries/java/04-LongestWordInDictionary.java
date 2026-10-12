/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd build a trie and require every prefix along a candidate's path to end a word.
 * - Among qualifying words I'd keep the longest, then use lexicographic order to break ties.
 * Time complexity: O(S) for total input characters S, including candidate walks and tie
 *   comparisons.
 * Space complexity: O(S) auxiliary trie space.
 */

// 720. Longest Word in Dictionary
// https://leetcode.com/problems/longest-word-in-dictionary/
// Solution preserves words and uses iterative traversal. LeetCode currently labels this Medium.
import java.util.*;

class Solution {
    private static class TrieNode {
        TrieNode[] child = new TrieNode[26];
        boolean end;
    }

    public String longestWord(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode node = root;
            for (char c : word.toCharArray()) {
                int i = c - 'a';
                if (node.child[i] == null) {
                    node.child[i] = new TrieNode();
                }
                node = node.child[i];
            }
            node.end = true;
        }
        String best = "";
        for (String word : words) {
            TrieNode node = root;
            boolean valid = true;
            for (char c : word.toCharArray()) {
                node = node.child[c - 'a'];
                if (!node.end) {
                    valid = false;
                    break;
                }
            }
            if (valid
                    && (word.length() > best.length()
                            || (word.length() == best.length() && word.compareTo(best) < 0))) {
                best = word;
            }
        }
        return best;
    }
}
