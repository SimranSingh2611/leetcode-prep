// 1905. Count Sub Islands
// https://leetcode.com/problems/count-sub-islands/
// Solution preserves grid1 and changes visited land in grid2 to zero.
import java.util.*;

class Solution {
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        int rows = grid2.length, cols = grid2[0].length, count = 0;
        Deque<int[]> stack = new ArrayDeque<>();
        int[] dr = {1, -1, 0, 0}, dc = {0, 0, 1, -1};
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid2[r][c] == 0) {
                    continue;
                }
                boolean valid = true;
                grid2[r][c] = 0;
                stack.push(new int[] {r, c});
                while (!stack.isEmpty()) {
                    int[] cell = stack.pop();
                    if (grid1[cell[0]][cell[1]] == 0) {
                        valid = false;
                    }
                    for (int d = 0; d < 4; d++) {
                        int nr = cell[0] + dr[d], nc = cell[1] + dc[d];
                        if (nr >= 0 && nc >= 0 && nr < rows && nc < cols && grid2[nr][nc] == 1) {
                            grid2[nr][nc] = 0;
                            stack.push(new int[] {nr, nc});
                        }
                    }
                }
                if (valid) {
                    count++;
                }
            }
        }
        return count;
    }
}
