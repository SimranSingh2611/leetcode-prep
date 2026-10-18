/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd maintain increasing bar heights and finalize a bar when a smaller height arrives.
 * - Its popped position supplies the height, while the new stack top and current index delimit
 *   the maximal width.
 * Time complexity: O(n) amortized overall for n bars.
 * Space complexity: O(n) auxiliary stack space.
 */

// 84. Largest Rectangle in Histogram
// https://leetcode.com/problems/largest-rectangle-in-histogram/
// NeetCode 150: https://neetcode.io/problems/largest-rectangle-in-histogram/question?list=neetcode150
// Guide template 6, merged smaller boundaries: pop >; non-decreasing heights.
// Fused adaptation: compute area at pop time instead of saving boundary arrays.
// O(n) time and O(n) auxiliary space; input preserved.
import java.util.*;

class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length, best = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i <= n; i++) {
            int current = i == n ? 0 : heights[i];
            // A virtual zero at n finishes positive-height bars; no input mutation.
            while (!stack.isEmpty() && heights[stack.peek()] > current) {
                int h = heights[stack.pop()], left = stack.isEmpty() ? -1 : stack.peek();
                // i is next strictly smaller; left is previous smaller-or-equal.
                // Equal bars stay; the leftmost equal bar eventually spans them all.
                best = Math.max(best, h * (i - left - 1));
            }
            if (i < n) {
                stack.push(i);
            }
        }
        return best;
    }
}
