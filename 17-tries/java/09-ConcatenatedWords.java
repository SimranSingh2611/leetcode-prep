/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd try dictionary prefixes from each start position and memoize how many pieces can cover
 *   the remaining suffix.
 * - A candidate qualifies only with at least two pieces, so its own full-word match cannot
 *   satisfy the test alone.
 * Time complexity: O(S + sum(L_i^2)) over word lengths L_i and total characters S.
 * Space complexity: O(S + Lmax) auxiliary trie/memo/stack, plus output references; Lmax is the
 *   longest word length.
 */

// 472. Concatenated Words
// https://leetcode.com/problems/concatenated-words/
// Solution preserves words. The memoization idea is introduced here; revisit this stretch problem
// after dynamic programming if needed.
import java.util.*;

class Solution {
    private static class TrieNode {
        TrieNode[] child = new TrieNode[26];
        boolean end;
    }

    public List<String> findAllConcatenatedWordsInADict(String[] words) {
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
        List<String> out = new ArrayList<>();
        for (String word : words) {
            int[] memo = new int[word.length() + 1];
            Arrays.fill(memo, -2);
            memo[word.length()] = 0;
            if (pieces(word, 0, root, memo) >= 2) {
                out.add(word);
            }
        }
        return out;
    }

    private int pieces(String word, int start, TrieNode root, int[] memo) {
        if (memo[start] != -2) {
            return memo[start];
        }
        TrieNode node = root;
        int best = -1;
        for (int i = start; i < word.length(); i++) {
            node = node.child[word.charAt(i) - 'a'];
            if (node == null) {
                break;
            }
            if (node.end) {
                int rest = pieces(word, i + 1, root, memo);
                if (rest >= 0) {
                    best = Math.max(best, 1 + rest);
                }
            }
        }
        return memo[start] = best;
    }
}
