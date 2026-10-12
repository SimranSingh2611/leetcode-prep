// 127. Word Ladder
// https://leetcode.com/problems/word-ladder/
// Solution copies wordList into a set and leaves the input list unchanged.
import java.util.*;

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord.equals(endWord)) {
            return 1;
        }
        Set<String> remaining = new HashSet<>(wordList);
        if (!remaining.contains(endWord)) {
            return 0;
        }
        ArrayDeque<String> queue = new ArrayDeque<>();
        queue.offer(beginWord);
        remaining.remove(beginWord);
        int length = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                String word = queue.poll();
                if (word.equals(endWord)) {
                    return length;
                }
                char[] chars = word.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    char saved = chars[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == saved) {
                            continue;
                        }
                        chars[i] = c;
                        String next = new String(chars);
                        if (remaining.remove(next)) {
                            queue.offer(next);
                        }
                    }
                    chars[i] = saved;
                }
            }
            length++;
        }
        return 0;
    }
}
