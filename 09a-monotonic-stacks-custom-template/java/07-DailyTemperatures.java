/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd stack unresolved day indices in non-increasing temperature order.
 * - A warmer day resolves colder tops, and its index difference gives their waiting times.
 * Time complexity: O(n) amortized overall for n days.
 * Space complexity: O(n) auxiliary stack, plus O(n) output.
 */

// 739. Daily Temperatures
// https://leetcode.com/problems/daily-temperatures/
// NeetCode 150: https://neetcode.io/problems/daily-temperatures/question?list=neetcode150
// Next Greater adaptation: forward scan, pop <, record the distance i - j.
// O(n) time and O(n) auxiliary space; input preserved.
import java.util.*;

class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack = new Stack<>();
        int n = temperatures.length;
        int[] out = new int[n];

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                int j = stack.pop();
                out[j] = i - j; // Current i answers waiting j with a number of days.
            }
            stack.push(i);
        }

        return out;
    }
}
