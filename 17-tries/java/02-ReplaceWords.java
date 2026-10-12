// 648. Replace Words
// https://leetcode.com/problems/replace-words/
// Solution assumes the problem's lowercase words and single-space sentence formatting; dictionary
// is unchanged.
import java.util.*;

class Solution {
    private static class TrieNode {
        TrieNode[] child = new TrieNode[26];
        boolean end;
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        TrieNode root = new TrieNode();
        for (String word : dictionary) {
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
        String[] words = sentence.split(" ");
        for (int w = 0; w < words.length; w++) {
            TrieNode node = root;
            String word = words[w];
            for (int i = 0; i < word.length(); i++) {
                node = node.child[word.charAt(i) - 'a'];
                if (node == null) {
                    break;
                }
                if (node.end) {
                    words[w] = word.substring(0, i + 1);
                    break;
                }
            }
        }
        return String.join(" ", words);
    }
}
