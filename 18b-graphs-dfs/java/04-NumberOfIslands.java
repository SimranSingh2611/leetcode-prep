// 200. Number of Islands
// https://leetcode.com/problems/number-of-islands/
// Solution mutates grid by replacing visited '1' cells with '0'.
import java.util.*;

class Solution {
    public int numIslands(char[][] grid) {
        int rows = grid.length, cols = grid[0].length, count = 0;
        int[] dr = {1, -1, 0, 0}, dc = {0, 0, 1, -1};
        ArrayDeque<int[]> stack = new ArrayDeque<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == '1') {
                    count++;
                    grid[r][c] = '0';
                    stack.push(new int[] {r, c});
                    while (!stack.isEmpty()) {
                        int[] p = stack.pop();
                        for (int d = 0; d < 4; d++) {
                            int nr = p[0] + dr[d], nc = p[1] + dc[d];
                            if (nr >= 0
                                    && nc >= 0
                                    && nr < rows
                                    && nc < cols
                                    && grid[nr][nc] == '1') {
                                grid[nr][nc] = '0';
                                stack.push(new int[] {nr, nc});
                            }
                        }
                    }
                }
            }
        }
        return count;
    }
}

// Retained breadth-first alternative for comparison; the primary Solution above uses DFS.
class BfsSolution {
    public int numIslands(char[][] grid) {
        int rows = grid.length, cols = grid[0].length, count = 0;
        int[] dr = {1, -1, 0, 0}, dc = {0, 0, 1, -1};
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == '1') {
                    count++;
                    grid[r][c] = '0';
                    queue.offer(new int[] {r, c});
                    while (!queue.isEmpty()) {
                        int[] p = queue.poll();
                        for (int d = 0; d < 4; d++) {
                            int nr = p[0] + dr[d], nc = p[1] + dc[d];
                            if (nr >= 0
                                    && nc >= 0
                                    && nr < rows
                                    && nc < cols
                                    && grid[nr][nc] == '1') {
                                grid[nr][nc] = '0';
                                queue.offer(new int[] {nr, nc});
                            }
                        }
                    }
                }
            }
        }
        return count;
    }
}
