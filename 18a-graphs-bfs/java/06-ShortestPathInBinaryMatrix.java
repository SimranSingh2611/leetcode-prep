// 1091. Shortest Path in Binary Matrix
// https://leetcode.com/problems/shortest-path-in-binary-matrix/
// Solution preserves grid. A one-cell open grid returns 1.
import java.util.*;

class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;
        if (grid[0][0] != 0 || grid[n - 1][n - 1] != 0) {
            return -1;
        }
        boolean[][] seen = new boolean[n][n];
        Queue<int[]> queue = new ArrayDeque<>();
        seen[0][0] = true;
        queue.offer(new int[] {0, 0});
        int distance = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cell = queue.poll();
                if (cell[0] == n - 1 && cell[1] == n - 1) {
                    return distance;
                }
                for (int dr = -1; dr <= 1; dr++) {
                    for (int dc = -1; dc <= 1; dc++) {
                        int r = cell[0] + dr, c = cell[1] + dc;
                        if (r >= 0 && c >= 0 && r < n && c < n && !seen[r][c] && grid[r][c] == 0) {
                            seen[r][c] = true;
                            queue.offer(new int[] {r, c});
                        }
                    }
                }
            }
            distance++;
        }
        return -1;
    }
}
