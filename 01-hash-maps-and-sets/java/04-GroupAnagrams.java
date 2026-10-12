/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd group words by their 26-letter frequency signature.
 * - Anagrams have identical signatures, so each word goes directly into its matching bucket.
 * Time complexity: O(n(k + 1)) expected for n words of maximum length k, including empty words;
 *   usually written O(nk).
 * Space complexity: O(n(k + 1)) is a safe bound for keys and result references; input strings
 *   are referenced, not copied.
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Recommended for lowercase English: O(n * k) time and O(n * k) result/key space.
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> groups = new HashMap<>();

        for (String word : strs) {
            int[] counts = new int[26];
            for (int i = 0; i < word.length(); i++) {
                counts[word.charAt(i) - 'a']++;
            }

            StringBuilder key = new StringBuilder();
            for (int count : counts) {
                key.append('#').append(count);
            }
            groups.computeIfAbsent(key.toString(), ignored -> new ArrayList<>()).add(word);
        }
        return new ArrayList<>(groups.values());
    }
}

// Simpler/general alphabet approach: O(n * k log k) time.
class SortingSolution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> groups = new HashMap<>();
        for (String word : strs) {
            char[] letters = word.toCharArray();
            Arrays.sort(letters);
            groups.computeIfAbsent(new String(letters), ignored -> new ArrayList<>()).add(word);
        }
        return new ArrayList<>(groups.values());
    }
}

