/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd consume each zero component while remembering whether it touches the border.
 * - Even after finding a border cell I'd finish the traversal, so the same open component
 *   cannot be counted later.
 * Time complexity: O(RC) for an R by C grid.
 * Space complexity: O(RC) auxiliary DFS stack; visited cells are marked in place.
 */

// 1254. Number of Closed Islands
// https://leetcode.com/problems/number-of-closed-islands/
// Solution mutates land 0s to water 1s. The land encoding is opposite to Number of Islands.
import java.util.*;

class Solution {
    public int closedIsland(int[][] grid) {
        int rows = grid.length, cols = grid[0].length, result = 0;
        int[] dr = {1, -1, 0, 0}, dc = {0, 0, 1, -1};
        Deque<int[]> stack = new ArrayDeque<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] != 0) {
                    continue;
                }
                boolean closed = true;
                grid[r][c] = 1;
                stack.push(new int[] {r, c});
                while (!stack.isEmpty()) {
                    int[] cell = stack.pop();
                    if (cell[0] == 0
                            || cell[1] == 0
                            || cell[0] == rows - 1
                            || cell[1] == cols - 1) {
                        closed = false;
                    }
                    for (int d = 0; d < 4; d++) {
                        int nr = cell[0] + dr[d], nc = cell[1] + dc[d];
                        if (nr >= 0 && nc >= 0 && nr < rows && nc < cols && grid[nr][nc] == 0) {
                            grid[nr][nc] = 1;
                            stack.push(new int[] {nr, nc});
                        }
                    }
                }
                if (closed) {
                    result++;
                }
            }
        }
        return result;
    }
}
