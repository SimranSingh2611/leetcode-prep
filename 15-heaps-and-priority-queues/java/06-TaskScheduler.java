// 621. Task Scheduler
// https://leetcode.com/problems/task-scheduler/
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
