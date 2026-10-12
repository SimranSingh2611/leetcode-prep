/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd BFS from the starting cell through non-wall neighbors, marking cells when queued.
 * - BFS explores increasing move counts, so the first food reached gives the shortest path.
 * Time complexity: O(RC) for an R by C grid.
 * Space complexity: O(RC) auxiliary visited/queue space.
 */

// 1730. Shortest Path to Get Food
// https://leetcode.com/problems/shortest-path-to-get-food/
// Solution preserves grid using a separate visited matrix. Premium screenshot selection.
import java.util.*;

class Solution {
    public int getFood(char[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        boolean[][] seen = new boolean[rows][cols];
        Queue<int[]> queue = new ArrayDeque<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == '*') {
                    seen[r][c] = true;
                    queue.offer(new int[] {r, c});
                }
            }
        }
        int[] dr = {1, -1, 0, 0}, dc = {0, 0, 1, -1};
        int distance = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] cell = queue.poll();
                if (grid[cell[0]][cell[1]] == '#') {
                    return distance;
                }
                for (int d = 0; d < 4; d++) {
                    int r = cell[0] + dr[d], c = cell[1] + dc[d];
                    if (r >= 0
                            && c >= 0
                            && r < rows
                            && c < cols
                            && !seen[r][c]
                            && grid[r][c] != 'X') {
                        seen[r][c] = true;
                        queue.offer(new int[] {r, c});
                    }
                }
            }
            distance++;
        }
        return -1;
    }
}
