/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd choose the most frequent available character from a max-heap.
 * - Holding the previous character outside the heap for one step prevents adjacent duplicates;
 *   leftover held copies with no alternative mean failure.
 * Time complexity: O(n log(U + 1)) for length n and U distinct characters; O(n) with U <= 26.
 * Space complexity: O(U) auxiliary heap/counts, plus O(n) output buffer; auxiliary space is
 *   O(1) for 26 letters.
 */

// 767. Reorganize String
// https://leetcode.com/problems/reorganize-string/
// Solution returns any valid arrangement or an empty string. Heap entries are mutated only after
// removal.
import java.util.*;

class Solution {
    public String reorganizeString(String s) {
        int[] counts = new int[26];
        for (int i = 0; i < s.length(); i++) {
            counts[s.charAt(i) - 'a']++;
        }
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> Integer.compare(b[1], a[1]));
        for (int i = 0; i < 26; i++) {
            if (counts[i] > 0) {
                heap.offer(new int[] {i, counts[i]});
            }
        }
        int[] previous = null;
        StringBuilder out = new StringBuilder();
        while (!heap.isEmpty()) {
            int[] current = heap.poll();
            out.append((char) ('a' + current[0]));
            current[1]--;
            if (previous != null && previous[1] > 0) {
                heap.offer(previous);
            }
            previous = current;
        }
        return previous != null && previous[1] > 0 ? "" : out.toString();
    }
}
