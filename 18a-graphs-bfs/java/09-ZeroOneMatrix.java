// 542. 01 Matrix
// https://leetcode.com/problems/01-matrix/
// Solution preserves mat and returns a new matrix.
import java.util.*;

class Solution {
    public int[][] updateMatrix(int[][] mat) {
        int rows = mat.length, cols = mat[0].length;
        int[][] distance = new int[rows][cols];
        Queue<int[]> queue = new ArrayDeque<>();
        for (int r = 0; r < rows; r++) {
            Arrays.fill(distance[r], -1);
            for (int c = 0; c < cols; c++) {
                if (mat[r][c] == 0) {
                    distance[r][c] = 0;
                    queue.offer(new int[] {r, c});
                }
            }
        }
        int[] dr = {1, -1, 0, 0}, dc = {0, 0, 1, -1};
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            for (int d = 0; d < 4; d++) {
                int r = cell[0] + dr[d], c = cell[1] + dc[d];
                if (r >= 0 && c >= 0 && r < rows && c < cols && distance[r][c] == -1) {
                    distance[r][c] = distance[cell[0]][cell[1]] + 1;
                    queue.offer(new int[] {r, c});
                }
            }
        }
        return distance;
    }
}
