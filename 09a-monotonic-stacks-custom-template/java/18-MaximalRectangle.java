/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd turn each row into histogram heights of consecutive ones ending there.
 * - A histogram helper combines previous/next strictly smaller boundaries for each row.
 * Time complexity: O(RC) for R rows and C columns.
 * Space complexity: O(C) auxiliary histogram, boundary arrays, and stack space.
 */

// 85. Maximal Rectangle
// https://leetcode.com/problems/maximal-rectangle/
// Reduce each row to a histogram, then reuse the two-pass smaller-boundary approach.
// Previous Smaller scans backward; Next Smaller scans forward; both pop >.
// O(rows * columns) time and O(columns) auxiliary space; matrix preserved.
import java.util.*;

class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        int[] heights = new int[matrix[0].length];
        int best = 0;
        for (char[] row : matrix) {
            for (int c = 0; c < heights.length; c++) {
                heights[c] = row[c] == '1' ? heights[c] + 1 : 0;
            }
            best = Math.max(best, largestRectangleArea(heights));
        }
        return best;
    }

    // Same boundary calculation as exercise 13; private so this file submits independently.
    private int largestRectangleArea(int[] heights) {
        int n = heights.length, best = 0;
        int[] left = new int[n], right = new int[n];
        Arrays.fill(left, -1);
        Arrays.fill(right, n);
        Stack<Integer> stack = new Stack<>();

        // Previous strictly smaller.
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && heights[stack.peek()] > heights[i]) {
                int j = stack.pop();
                left[j] = i;
            }
            stack.push(i);
        }

        stack.clear();
        // Next strictly smaller.
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
