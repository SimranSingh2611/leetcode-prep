/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd schedule blocks of cooldown plus one slots, selecting distinct tasks with the largest
 *   remaining counts.
 * - Unfinished tasks return only after the block; the final block counts actual tasks rather
 *   than trailing idle slots.
 * Time complexity: O(N log(U + 1)) for N tasks and U distinct types; the formula alternative
 *   takes O(N + U). Both are O(N) when U <= 26.
 * Space complexity: O(U) auxiliary heap/counts/pending storage; O(1) under the fixed 26-letter
 *   alphabet.
 */

// 621. Task Scheduler
// https://leetcode.com/problems/task-scheduler/
// NeetCode 150: https://neetcode.io/problems/task-scheduling/question?list=neetcode150
// Solution demonstrates heap scheduling. FormulaSolution is a simpler counting follow-up, not an
// asymptotic improvement under a fixed 26-letter alphabet.
import java.util.*;

class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        for (char c : tasks) {
            freq[c - 'A']++;
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>(Collections.reverseOrder());
        for (int f : freq) {
            if (f > 0) {
                heap.offer(f);
            }
        }
        int time = 0;
        while (!heap.isEmpty()) {
            List<Integer> pending = new ArrayList<>();
            int used = 0;
            for (int slot = 0; slot <= n && !heap.isEmpty(); slot++) {
                int f = heap.poll() - 1;
                used++;
                if (f > 0) {
                    pending.add(f);
                }
            }
            heap.addAll(pending);
            time += heap.isEmpty() ? used : n + 1;
        }
        return time;
    }
}

class FormulaSolution {
    public int leastInterval(char[] tasks, int n) {
        int[] freq = new int[26];
        for (char c : tasks) {
            freq[c - 'A']++;
        }
        int max = 0, tied = 0;
        for (int f : freq) {
            max = Math.max(max, f);
        }
        for (int f : freq) {
            if (f == max) {
                tied++;
            }
        }
        return Math.max(tasks.length, (max - 1) * (n + 1) + tied);
    }
}
