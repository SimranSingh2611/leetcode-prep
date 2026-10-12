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
