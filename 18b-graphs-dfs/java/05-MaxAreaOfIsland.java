// 695. Max Area of Island
// https://leetcode.com/problems/max-area-of-island/
// Solution destroys land marks by changing visited 1s to 0s; clone the grid first if preservation
// is required.
import java.util.*;

class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int rows = grid.length, cols = grid[0].length, best = 0;
        int[] dr = {1, -1, 0, 0}, dc = {0, 0, 1, -1};
        ArrayDeque<int[]> stack = new ArrayDeque<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1) {
                    int area = 0;
                    grid[r][c] = 0;
                    stack.push(new int[] {r, c});
                    while (!stack.isEmpty()) {
                        int[] p = stack.pop();
                        area++;
                        for (int d = 0; d < 4; d++) {
                            int nr = p[0] + dr[d], nc = p[1] + dc[d];
                            if (nr >= 0 && nc >= 0 && nr < rows && nc < cols && grid[nr][nc] == 1) {
                                grid[nr][nc] = 0;
                                stack.push(new int[] {nr, nc});
                            }
                        }
                    }
                    best = Math.max(best, area);
                }
            }
        }
        return best;
    }
}
