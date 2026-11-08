/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd keep unresolved indices in non-decreasing value order.
 * - A smaller incoming value resolves each larger top as its next smaller answer; equal values
 *   remain pending.
 * Time complexity: O(n) amortized overall for n values.
 * Space complexity: O(n) auxiliary stack, plus O(n) output indices.
 */

// Next Smaller — standalone template exercise (no LeetCode problem ID).
// https://leetcode.com/discuss/post/2347639/a-comprehensive-guide-and-template-for-m-irii/
// Next strictly smaller: scan left to right, pop >, resolve the popped index.
// Stack values are non-decreasing from bottom to top; equal values keep waiting.
// Return nearest strictly smaller indices to the right, or -1 if absent.
// O(n) time and O(n) auxiliary space; input preserved. Equal values do not qualify.
import java.util.*;

class Solution {
    public int[] nextSmallerIndexes(int[] values) {
        int[] answer = new int[values.length];
        Arrays.fill(answer, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < values.length; i++) {
            while (!stack.isEmpty() && values[stack.peek()] > values[i]) {
                int j = stack.pop();
                answer[j] = i; // Current i answers waiting j: next strictly smaller.
            }
            stack.push(i);
        }
        return answer;
    }
}
