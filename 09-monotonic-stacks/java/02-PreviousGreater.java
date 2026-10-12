/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd remove previous values no greater than the current value from the stack.
 * - The surviving top is the nearest strictly greater predecessor, then I'd push the current
 *   index.
 * Time complexity: O(n) amortized overall for n values.
 * Space complexity: O(n) auxiliary stack, plus O(n) output indices.
 */

// Previous Greater — standalone template exercise (no LeetCode problem ID).
// https://leetcode.com/discuss/post/2347639/a-comprehensive-guide-and-template-for-m-irii/
// Guide template 2: pop <=; strictly decreasing values from bottom to top.
// Return nearest strictly greater indices to the left, or -1 if absent.
// O(n) time and O(n) auxiliary space; input preserved. Equal values do not qualify.
import java.util.*;

class Solution {
    public int[] previousGreaterIndexes(int[] values) {
        int[] answer = new int[values.length];
        Arrays.fill(answer, -1);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < values.length; i++) {
            while (!stack.isEmpty() && values[stack.peek()] <= values[i]) {
                // Remove candidates that cannot be the strictly qualifying predecessor.
                stack.pop();
            }
            // Query AFTER while: the surviving top is the nearest qualifying predecessor.
            if (!stack.isEmpty()) {
                answer[i] = stack.peek();
            }
            stack.push(i);
        }
        return answer;
    }
}
