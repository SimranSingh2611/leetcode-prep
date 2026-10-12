/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd heap athlete indices by score and extract them in descending order.
 * - Writing each medal or rank at its original index preserves the requested answer order.
 * Time complexity: O(n log(n + 1)) for n athletes.
 * Space complexity: O(n) auxiliary heap; O(n) result references plus rank-label characters.
 */

// 506. Relative Ranks
// https://leetcode.com/problems/relative-ranks/
// Solution leaves scores unchanged. This is a warm-up, not a case where a heap asymptotically beats
// sorting.
import java.util.*;

class Solution {
    public String[] findRelativeRanks(int[] score) {
        PriorityQueue<Integer> heap =
                new PriorityQueue<>((a, b) -> Integer.compare(score[b], score[a]));
        for (int i = 0; i < score.length; i++) {
            heap.offer(i);
        }
        String[] out = new String[score.length];
        String[] medals = {"Gold Medal", "Silver Medal", "Bronze Medal"};
        int rank = 1;
        while (!heap.isEmpty()) {
            int i = heap.poll();
            out[i] = rank <= 3 ? medals[rank - 1] : Integer.toString(rank);
            rank++;
        }
        return out;
    }
}
