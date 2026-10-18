/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd record each character's final occurrence and extend the current piece's end as I scan.
 * - Reaching that end is the earliest safe cut, maximizing the number of pieces.
 * Time complexity: O(n) for string length n.
 * Space complexity: O(1) auxiliary last-occurrence state for 26 letters, plus output partition
 *   lengths.
 */

// 763. Partition Labels
// https://leetcode.com/problems/partition-labels/
// NeetCode 150: https://neetcode.io/problems/partition-labels/question?list=neetcode150
// Solution preserves s and returns partition lengths.
import java.util.*;

class Solution {
    public List<Integer> partitionLabels(String s) {
        int[] last = new int[26];
        for (int i = 0; i < s.length(); i++) {
            last[s.charAt(i) - 'a'] = i;
        }
        List<Integer> out = new ArrayList<>();
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            end = Math.max(end, last[s.charAt(i) - 'a']);
            if (i == end) {
                out.add(end - start + 1);
                start = i + 1;
            }
        }
        return out;
    }
}
