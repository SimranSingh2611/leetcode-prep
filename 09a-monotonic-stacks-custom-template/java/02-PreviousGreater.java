/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd scan right to left, keeping indices waiting for a greater value on their left.
 * - A larger current value resolves each smaller top; equal values keep waiting.
 * Time complexity: O(n) amortized overall for n values.
 * Space complexity: O(n) auxiliary stack, plus O(n) output indices.
 */

// Previous Greater — standalone template exercise (no LeetCode problem ID).
// https://leetcode.com/discuss/post/2347639/a-comprehensive-guide-and-template-for-m-irii/
// Previous strictly greater: scan right to left, pop <, resolve the popped index.
// Same comparison as Next Greater; stack values are non-increasing bottom to top.
// Return nearest strictly greater indices to the left, or -1 if absent.
// O(n) time and O(n) auxiliary space; input preserved. Equal values do not qualify.
import java.util.*;

class Solution {
    public int[] previousGreaterIndexes(int[] values) {
        int[] answer = new int[values.length];
        Arrays.fill(answer, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = values.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && values[stack.peek()] < values[i]) {
                int j = stack.pop();
                answer[j] = i; // Current i answers waiting j: previous strictly greater.
            }
            stack.push(i);
        }
        return answer;
    }
}
