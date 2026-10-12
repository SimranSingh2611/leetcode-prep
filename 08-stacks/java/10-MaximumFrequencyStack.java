// 895. Maximum Frequency Stack
// https://leetcode.com/problems/maximum-frequency-stack/
// FreqStack design class; grouping-by-frequency hybrid.
import java.util.*;

class FreqStack {
    private final Map<Integer, Integer> frequency = new HashMap<>();
    private final Map<Integer, Deque<Integer>> groups = new HashMap<>();
    private int maximum;

    public void push(int val) {
        int f = frequency.getOrDefault(val, 0) + 1;
        frequency.put(val, f);
        groups.computeIfAbsent(f, k -> new ArrayDeque<>()).push(val);
        maximum = Math.max(maximum, f);
    }

    public int pop() {
        Deque<Integer> group = groups.get(maximum);
        int val = group.pop();
        int next = frequency.get(val) - 1;
        if (next == 0) {
            frequency.remove(val);
        } else {
            frequency.put(val, next);
        }
        if (group.isEmpty()) {
            groups.remove(maximum);
            maximum--;
        }
        return val;
    }
}
