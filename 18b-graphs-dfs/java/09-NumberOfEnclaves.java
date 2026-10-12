/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd erase every land cell reachable from border land.
 * - The remaining land cannot escape the grid, so summing it directly gives the enclave area.
 * Time complexity: O(RC) for an R by C grid.
 * Space complexity: O(RC) auxiliary DFS stack; marks the grid in place.
 */

// 1020. Number of Enclaves
// https://leetcode.com/problems/number-of-enclaves/
// Solution mutates boundary-connected land to zero; enclosed land stays one.
import java.util.*;

class Solution {
    public int numEnclaves(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        Deque<int[]> stack = new ArrayDeque<>();
        for (int r = 0; r < rows; r++) {
            mark(grid, r, 0, stack);
            mark(grid, r, cols - 1, stack);
        }
        for (int c = 0; c < cols; c++) {
            mark(grid, 0, c, stack);
            mark(grid, rows - 1, c, stack);
        }
        int[] dr = {1, -1, 0, 0}, dc = {0, 0, 1, -1};
        while (!stack.isEmpty()) {
            int[] cell = stack.pop();
            for (int d = 0; d < 4; d++) {
                mark(grid, cell[0] + dr[d], cell[1] + dc[d], stack);
            }
        }
        int count = 0;
        for (int[] row : grid) {
            for (int value : row) {
                count += value;
            }
        }
        return count;
    }

    private void mark(int[][] grid, int r, int c, Deque<int[]> stack) {
        if (r >= 0 && c >= 0 && r < grid.length && c < grid[0].length && grid[r][c] == 1) {
            grid[r][c] = 0;
            stack.push(new int[] {r, c});
        }
    }
}
