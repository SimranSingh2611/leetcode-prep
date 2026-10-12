/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd start BFS from every land cell and expand into water.
 * - The final layer measures the farthest cell's distance to its nearest land; all-land and
 *   all-water inputs return failure.
 * Time complexity: O(n^2) for an n by n grid.
 * Space complexity: O(n^2) auxiliary visited/queue space.
 */

// 1162. As Far from Land as Possible
// https://leetcode.com/problems/as-far-from-land-as-possible/
// Solution preserves grid using a separate visited matrix.
import java.util.*;

class Solution {
    public int maxDistance(int[][] grid) {
        int n = grid.length;
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] seen = new boolean[n][n];
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 1) {
                    seen[r][c] = true;
                    queue.offer(new int[] {r, c});
                }
            }
        }
        if (queue.isEmpty() || queue.size() == n * n) {
            return -1;
        }
        int distance = -1;
        int[] dr = {1, -1, 0, 0}, dc = {0, 0, 1, -1};
        while (!queue.isEmpty()) {
            int size = queue.size();
            distance++;
            for (int i = 0; i < size; i++) {
                int[] cell = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int r = cell[0] + dr[d], c = cell[1] + dc[d];
                    if (r >= 0 && c >= 0 && r < n && c < n && !seen[r][c]) {
                        seen[r][c] = true;
                        queue.offer(new int[] {r, c});
                    }
                }
            }
        }
        return distance;
    }
}
