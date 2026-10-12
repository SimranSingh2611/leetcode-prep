// 85. Maximal Rectangle
// https://leetcode.com/problems/maximal-rectangle/
// Guide template 6 per row: merged next < / previous <= with pop >.
// Histogram adaptation computes area on pop; a virtual zero flushes positive bars.
// O(rows * columns) time and O(columns) auxiliary space; matrix preserved.
import java.util.*;

class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        int[] h = new int[matrix[0].length];
        int best = 0;
        for (char[] row : matrix) {
            for (int c = 0; c < h.length; c++) {
                h[c] = row[c] == '1' ? h[c] + 1 : 0;
            }
            Deque<Integer> stack = new ArrayDeque<>();
            for (int i = 0; i <= h.length; i++) {
                int current = i == h.length ? 0 : h[i];
                while (!stack.isEmpty() && h[stack.peek()] > current) {
                    int height = h[stack.pop()], left = stack.isEmpty() ? -1 : stack.peek();
                    // Current i is next <; surviving top is previous <=.
                    best = Math.max(best, height * (i - left - 1));
                }
                if (i < h.length) {
                    stack.push(i);
                }
            }
        }
        return best;
    }
}
