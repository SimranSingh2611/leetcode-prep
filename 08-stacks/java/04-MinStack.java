/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd store each value with the minimum at its stack depth.
 * - Popping restores the previous minimum automatically, so querying the minimum never needs a
 *   scan.
 * Time complexity: O(1) per push, pop, top, or getMin, for both supplied designs.
 * Space complexity: O(n) persistent space for n stack entries; the difference-encoding
 *   alternative changes entry size, not the bound.
 */

// 155. Min Stack
// https://leetcode.com/problems/min-stack/
// NeetCode 150: https://neetcode.io/problems/minimum-stack/question?list=neetcode150
// MinStack recommended; DifferenceMinStack is a storage-layout alternative.
import java.util.*;

class MinStack {
    private final Deque<int[]> stack = new ArrayDeque<>();

    public void push(int val) {
        stack.push(new int[] {val, stack.isEmpty() ? val : Math.min(val, stack.peek()[1])});
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek()[0];
    }

    public int getMin() {
        return stack.peek()[1];
    }
}

class DifferenceMinStack {
    private final Deque<Long> differences = new ArrayDeque<>();
    private long minimum;

    public void push(int val) {
        if (differences.isEmpty()) {
            minimum = val;
            differences.push(0L);
            return;
        }
        long difference = (long) val - minimum;
        differences.push(difference);
        if (difference < 0) {
            minimum = val;
        }
    }

    public void pop() {
        long difference = differences.pop();
        if (difference < 0) {
            minimum -= difference;
        }
    }

    public int top() {
        return (int) (differences.peek() < 0 ? minimum : minimum + differences.peek());
    }

    public int getMin() {
        return (int) minimum;
    }
}
