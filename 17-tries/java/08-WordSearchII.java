/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd backtrack over board paths while following dictionary trie edges.
 * - Missing prefixes prune whole groups of words, and clearing a found terminal prevents
 *   duplicate answers.
 * Time complexity: O(S + RC 3^L) loose worst-case bound for dictionary characters S, board size
 *   R by C, and longest word length L.
 * Space complexity: O(S + min(L, RC)) auxiliary trie/stack space, excluding output word
 *   references.
 */

// 212. Word Search II
// https://leetcode.com/problems/word-search-ii/
// Solution restores board. It builds a fresh trie per call and clears only that private trie's
// terminal markers.
import java.util.*;

class Solution {
    private static class TrieNode {
        TrieNode[] child = new TrieNode[26];
        String word;
    }

    public List<String> findWords(char[][] board, String[] words) {
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
            node.word = word;
        }
        List<String> out = new ArrayList<>();
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                dfs(board, r, c, root, out);
            }
        }
        return out;
    }

    private void dfs(char[][] b, int r, int c, TrieNode parent, List<String> out) {
        if (r < 0 || c < 0 || r == b.length || c == b[0].length || b[r][c] == '#') {
            return;
        }
        char saved = b[r][c];
        TrieNode node = parent.child[saved - 'a'];
        if (node == null) {
            return;
        }
        if (node.word != null) {
            out.add(node.word);
            node.word = null;
        }
        b[r][c] = '#';
        dfs(b, r + 1, c, node, out);
        dfs(b, r - 1, c, node, out);
        dfs(b, r, c + 1, node, out);
        dfs(b, r, c - 1, node, out);
        b[r][c] = saved;
    }
}
