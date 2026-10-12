/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd direct each neighbor relation from the smaller value to the larger, creating an acyclic
 *   graph.
 * - Peeling complete zero-indegree layers gives the longest increasing path length because each
 *   cell waits for all smaller predecessors.
 * Time complexity: O(RC) for an R by C matrix.
 * Space complexity: O(RC) auxiliary indegree/queue storage; neighbor edges are generated on
 *   demand.
 */

// 329. Longest Increasing Path in a Matrix
// https://leetcode.com/problems/longest-increasing-path-in-a-matrix/
// Solution is iterative and preserves matrix; strict inequality is essential for acyclicity.
import java.util.*;

class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length;
        int[][] degree = new int[rows][cols];
        int[] dr = {1, -1, 0, 0}, dc = {0, 0, 1, -1};
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d], nc = c + dc[d];
                    if (nr >= 0
                            && nc >= 0
                            && nr < rows
                            && nc < cols
                            && matrix[nr][nc] < matrix[r][c]) {
                        degree[r][c]++;
                    }
                }
                if (degree[r][c] == 0) {
                    queue.offer(new int[] {r, c});
                }
            }
        }
        int layers = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            layers++;
            while (size-- > 0) {
                int[] p = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int r = p[0] + dr[d], c = p[1] + dc[d];
                    if (r >= 0
                            && c >= 0
                            && r < rows
                            && c < cols
                            && matrix[r][c] > matrix[p[0]][p[1]]
                            && --degree[r][c] == 0) {
                        queue.offer(new int[] {r, c});
                    }
                }
            }
        }
        return layers;
    }
}
