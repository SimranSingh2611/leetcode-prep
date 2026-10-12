/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd traverse the land component and count every edge facing water or the grid boundary.
 * - The scan follow-up instead adds four per land cell and subtracts shared edges, avoiding
 *   traversal storage.
 * Time complexity: O(RC) for an R by C grid, for all supplied variants.
 * Space complexity: Primary DFS/BFS: O(RC) visited/frontier space; scan alternative: O(1)
 *   auxiliary space.
 */

// 463. Island Perimeter
// https://leetcode.com/problems/island-perimeter/
// All variants preserve grid. ScanSolution is the space-optimized follow-up and illustrates when
// a graph model does not require traversal.
import java.util.*;

class Solution {
    public int islandPerimeter(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        boolean[][] seen = new boolean[rows][cols];
        ArrayDeque<int[]> stack = new ArrayDeque<>();
        outer:
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1) {
                    seen[r][c] = true;
                    stack.push(new int[] {r, c});
                    break outer;
                }
            }
        }
        int perimeter = 0;
        int[] dr = {1, -1, 0, 0}, dc = {0, 0, 1, -1};
        while (!stack.isEmpty()) {
            int[] p = stack.pop();
            for (int d = 0; d < 4; d++) {
                int r = p[0] + dr[d], c = p[1] + dc[d];
                if (r < 0 || c < 0 || r == rows || c == cols || grid[r][c] == 0) {
                    perimeter++;
                } else if (!seen[r][c]) {
                    seen[r][c] = true;
                    stack.push(new int[] {r, c});
                }
            }
        }
        return perimeter;
    }
}

class ScanSolution {
    public int islandPerimeter(int[][] grid) {
        int perimeter = 0;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == 1) {
                    perimeter += 4;
                    if (r > 0 && grid[r - 1][c] == 1) {
                        perimeter -= 2;
                    }
                    if (c > 0 && grid[r][c - 1] == 1) {
                        perimeter -= 2;
                    }
                }
            }
        }
        return perimeter;
    }
}

// Retained breadth-first alternative for comparison; the primary Solution above uses DFS.
class BfsSolution {
    public int islandPerimeter(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        boolean[][] seen = new boolean[rows][cols];
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        outer:
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1) {
                    seen[r][c] = true;
                    queue.offer(new int[] {r, c});
                    break outer;
                }
            }
        }
        int perimeter = 0;
        int[] dr = {1, -1, 0, 0}, dc = {0, 0, 1, -1};
        while (!queue.isEmpty()) {
            int[] p = queue.poll();
            for (int d = 0; d < 4; d++) {
                int r = p[0] + dr[d], c = p[1] + dc[d];
                if (r < 0 || c < 0 || r == rows || c == cols || grid[r][c] == 0) {
                    perimeter++;
                } else if (!seen[r][c]) {
                    seen[r][c] = true;
                    queue.offer(new int[] {r, c});
                }
            }
        }
        return perimeter;
    }
}
