/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd insert words normally, but let a dot in a search branch over every existing child.
 * - A match must end at a terminal node, so matching a prefix of a longer word is insufficient.
 * Time complexity: addWord: O(L); search: O(V) visited-node work, loosely bounded by O(L 26^d)
 *   for d dots in a length-L pattern.
 * Space complexity: O(S) persistent trie storage and O(L) recursive search stack.
 */

// 211. Design Add and Search Words Data Structure
// https://leetcode.com/problems/design-add-and-search-words-data-structure/
// WordDictionary is the required design class; '.' matches exactly one character.
import java.util.*;

class WordDictionary {
    private static class TrieNode {
        TrieNode[] child = new TrieNode[26];
        boolean end;
    }

    private final TrieNode root = new TrieNode();

    public WordDictionary() {}

    public void addWord(String word) {
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

    public boolean search(String word) {
        return match(root, word, 0);
    }

    private boolean match(TrieNode node, String word, int i) {
        if (i == word.length()) {
            return node.end;
        }
        char c = word.charAt(i);
        if (c != '.') {
            return node.child[c - 'a'] != null && match(node.child[c - 'a'], word, i + 1);
        }
        for (TrieNode next : node.child) {
            if (next != null && match(next, word, i + 1)) {
                return true;
            }
        }
        return false;
    }
}
