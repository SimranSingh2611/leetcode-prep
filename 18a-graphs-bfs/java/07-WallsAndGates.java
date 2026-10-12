/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd enqueue all gates before starting BFS.
 * - Each unfilled room gets its distance when first discovered, making the nearest gate win
 *   automatically.
 * Time complexity: O(RC) for an R by C grid.
 * Space complexity: O(RC) auxiliary queue; the rooms array stores visited distances in place.
 */

// 286. Walls and Gates
// https://leetcode.com/problems/walls-and-gates/
// Solution updates rooms in place. Premium NeetCode 150 and screenshot selection.
import java.util.*;

class Solution {
    public void wallsAndGates(int[][] rooms) {
        if (rooms.length == 0 || rooms[0].length == 0) {
            return;
        }
        int rows = rooms.length, cols = rooms[0].length;
        Queue<int[]> queue = new ArrayDeque<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (rooms[r][c] == 0) {
                    queue.offer(new int[] {r, c});
                }
            }
        }
        int[] dr = {1, -1, 0, 0}, dc = {0, 0, 1, -1};
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            for (int d = 0; d < 4; d++) {
                int r = cell[0] + dr[d], c = cell[1] + dc[d];
                if (r >= 0 && c >= 0 && r < rows && c < cols && rooms[r][c] == Integer.MAX_VALUE) {
                    rooms[r][c] = rooms[cell[0]][cell[1]] + 1;
                    queue.offer(new int[] {r, c});
                }
            }
        }
    }
}
