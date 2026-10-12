// 994. Rotting Oranges
// https://leetcode.com/problems/rotting-oranges/
// Solution mutates grid by changing newly rotten oranges from 1 to 2.
import java.util.*;

class Solution {
    public int orangesRotting(int[][] grid) {
        int rows = grid.length, cols = grid[0].length, fresh = 0, minutes = 0;
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        int[] dr = {1, -1, 0, 0}, dc = {0, 0, 1, -1};
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 2) {
                    queue.offer(new int[] {r, c});
                } else if (grid[r][c] == 1) {
                    fresh++;
                }
            }
        }
        while (fresh > 0 && !queue.isEmpty()) {
            int size = queue.size();
            minutes++;
            while (size-- > 0) {
                int[] p = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int r = p[0] + dr[d], c = p[1] + dc[d];
                    if (r >= 0 && c >= 0 && r < rows && c < cols && grid[r][c] == 1) {
                        grid[r][c] = 2;
                        fresh--;
                        queue.offer(new int[] {r, c});
                    }
                }
            }
        }
        return fresh == 0 ? minutes : -1;
    }
}
