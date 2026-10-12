/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd reverse the water flow and explore uphill or level from each ocean's boundary.
 * - Intersecting those two reached sets finds cells that can flow downhill to both oceans.
 * Time complexity: O(RC) for an R by C height grid.
 * Space complexity: O(RC) auxiliary reached-set/stack storage, plus up to O(RC) output.
 */

// 417. Pacific Atlantic Water Flow
// https://leetcode.com/problems/pacific-atlantic-water-flow/
// Solution preserves heights and returns coordinates in row-major order; any order is accepted.
import java.util.*;

class Solution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int rows = heights.length, cols = heights[0].length;
        boolean[][] pacific = new boolean[rows][cols], atlantic = new boolean[rows][cols];
        ArrayDeque<int[]> p = new ArrayDeque<>(), a = new ArrayDeque<>();
        for (int r = 0; r < rows; r++) {
            seed(r, 0, pacific, p);
            seed(r, cols - 1, atlantic, a);
        }
        for (int c = 0; c < cols; c++) {
            seed(0, c, pacific, p);
            seed(rows - 1, c, atlantic, a);
        }
        flood(heights, pacific, p);
        flood(heights, atlantic, a);
        List<List<Integer>> out = new ArrayList<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (pacific[r][c] && atlantic[r][c]) {
                    out.add(Arrays.asList(r, c));
                }
            }
        }
        return out;
    }

    private void seed(int r, int c, boolean[][] seen, ArrayDeque<int[]> stack) {
        if (!seen[r][c]) {
            seen[r][c] = true;
            stack.push(new int[] {r, c});
        }
    }

    private void flood(int[][] h, boolean[][] seen, ArrayDeque<int[]> stack) {
        int[] dr = {1, -1, 0, 0}, dc = {0, 0, 1, -1};
        while (!stack.isEmpty()) {
            int[] p = stack.pop();
            for (int d = 0; d < 4; d++) {
                int r = p[0] + dr[d], c = p[1] + dc[d];
                if (r >= 0
                        && c >= 0
                        && r < h.length
                        && c < h[0].length
                        && !seen[r][c]
                        && h[r][c] >= h[p[0]][p[1]]) {
                    seed(r, c, seen, stack);
                }
            }
        }
    }
}

// Retained breadth-first alternative for comparison; the primary Solution above uses DFS.
class BfsSolution {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int rows = heights.length, cols = heights[0].length;
        boolean[][] pacific = new boolean[rows][cols], atlantic = new boolean[rows][cols];
        ArrayDeque<int[]> p = new ArrayDeque<>(), a = new ArrayDeque<>();
        for (int r = 0; r < rows; r++) {
            seed(r, 0, pacific, p);
            seed(r, cols - 1, atlantic, a);
        }
        for (int c = 0; c < cols; c++) {
            seed(0, c, pacific, p);
            seed(rows - 1, c, atlantic, a);
        }
        flood(heights, pacific, p);
        flood(heights, atlantic, a);
        List<List<Integer>> out = new ArrayList<>();
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (pacific[r][c] && atlantic[r][c]) {
                    out.add(Arrays.asList(r, c));
                }
            }
        }
        return out;
    }

    private void seed(int r, int c, boolean[][] seen, ArrayDeque<int[]> q) {
        if (!seen[r][c]) {
            seen[r][c] = true;
            q.offer(new int[] {r, c});
        }
    }

    private void flood(int[][] h, boolean[][] seen, ArrayDeque<int[]> q) {
        int[] dr = {1, -1, 0, 0}, dc = {0, 0, 1, -1};
        while (!q.isEmpty()) {
            int[] p = q.poll();
            for (int d = 0; d < 4; d++) {
                int r = p[0] + dr[d], c = p[1] + dc[d];
                if (r >= 0
                        && c >= 0
                        && r < h.length
                        && c < h[0].length
                        && !seen[r][c]
                        && h[r][c] >= h[p[0]][p[1]]) {
                    seed(r, c, seen, q);
                }
            }
        }
    }
}
