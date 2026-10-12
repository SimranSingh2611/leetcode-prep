// 1293. Shortest Path in a Grid with Obstacles Elimination
// https://leetcode.com/problems/shortest-path-in-a-grid-with-obstacles-elimination/
// Both preserve grid. DominanceSolution optimizes visited storage; neither changes the distance
// objective. The start and destination are guaranteed empty.
import java.util.*;

class Solution {
    public int shortestPath(int[][] grid, int k) {
        int rows = grid.length, cols = grid[0].length;
        if (k >= rows + cols - 2) {
            return rows + cols - 2;
        }
        boolean[][][] seen = new boolean[rows][cols][k + 1];
        Queue<int[]> queue = new ArrayDeque<>();
        seen[0][0][k] = true;
        queue.offer(new int[] {0, 0, k});
        int[] dr = {1, -1, 0, 0}, dc = {0, 0, 1, -1};
        int distance = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] state = queue.poll();
                if (state[0] == rows - 1 && state[1] == cols - 1) {
                    return distance;
                }
                for (int d = 0; d < 4; d++) {
                    int r = state[0] + dr[d], c = state[1] + dc[d];
                    if (r < 0 || c < 0 || r >= rows || c >= cols) {
                        continue;
                    }
                    int remaining = state[2] - grid[r][c];
                    if (remaining >= 0 && !seen[r][c][remaining]) {
                        seen[r][c][remaining] = true;
                        queue.offer(new int[] {r, c, remaining});
                    }
                }
            }
            distance++;
        }
        return -1;
    }
}

class DominanceSolution {
    public int shortestPath(int[][] grid, int k) {
        int rows = grid.length, cols = grid[0].length;
        if (k >= rows + cols - 2) {
            return rows + cols - 2;
        }
        int[][] best = new int[rows][cols];
        for (int[] row : best) {
            Arrays.fill(row, -1);
        }
        Queue<int[]> queue = new ArrayDeque<>();
        best[0][0] = k;
        queue.offer(new int[] {0, 0, k});
        int[] dr = {1, -1, 0, 0}, dc = {0, 0, 1, -1};
        int distance = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] state = queue.poll();
                if (state[0] == rows - 1 && state[1] == cols - 1) {
                    return distance;
                }
                for (int d = 0; d < 4; d++) {
                    int r = state[0] + dr[d], c = state[1] + dc[d];
                    if (r < 0 || c < 0 || r >= rows || c >= cols) {
                        continue;
                    }
                    int remaining = state[2] - grid[r][c];
                    if (remaining >= 0 && remaining > best[r][c]) {
                        best[r][c] = remaining;
                        queue.offer(new int[] {r, c, remaining});
                    }
                }
            }
            distance++;
        }
        return -1;
    }
}
