/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd find each bar's previous and next strictly smaller indices in separate passes.
 * - The bar spans the space between those boundaries; equal heights may share a rectangle.
 * Time complexity: O(n) amortized overall for n bars.
 * Space complexity: O(n) auxiliary boundary arrays and stack space.
 */

// 84. Largest Rectangle in Histogram
// https://leetcode.com/problems/largest-rectangle-in-histogram/
// NeetCode 150: https://neetcode.io/problems/largest-rectangle-in-histogram/question?list=neetcode150
// Combine Previous Smaller (reverse scan) and Next Smaller (forward scan), both pop >.
// Both boundaries are strict: overlapping equal-height rectangles are safe for a maximum.
// O(n) time and O(n) auxiliary space; input preserved.
import java.util.*;

class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length, best = 0;
        int[] left = new int[n], right = new int[n];
        Arrays.fill(left, -1);
        Arrays.fill(right, n);
        Stack<Integer> stack = new Stack<>();

        // Previous strictly smaller: current i answers waiting indices to its right.
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                int j = stack.pop();
                left[j] = i;
            }
            stack.push(i);
        }

        stack.clear(); // The next pass has a fresh waiting list.
        // Next strictly smaller: current i answers waiting indices to its left.
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                int j = stack.pop();
                right[j] = i;
            }
            stack.push(i);
        }

        for (int i = 0; i < n; i++) {
            int width = right[i] - left[i] - 1;
            best = Math.max(best, heights[i] * width);
        }
        return best;
    }
}
