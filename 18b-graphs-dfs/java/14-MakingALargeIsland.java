/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd label islands with distinct IDs and record their areas.
 * - For each zero I'd add one and the areas of its distinct neighboring IDs, avoiding double
 *   counting the same island.
 * Time complexity: O(n^2) expected for an n by n grid.
 * Space complexity: O(n^2) auxiliary component-area/stack storage; at most four neighbor IDs
 *   need deduplication per zero.
 */

// 827. Making A Large Island
// https://leetcode.com/problems/making-a-large-island/
// Solution mutates grid: land 1s become component IDs 2 and above. Iterative traversal handles
// large components without recursive stack overflow.
import java.util.*;

class Solution {
    public int largestIsland(int[][] grid) {
        int n = grid.length, id = 2, best = 0;
        Map<Integer, Integer> area = new HashMap<>();
        int[] dr = {1, -1, 0, 0}, dc = {0, 0, 1, -1};
        ArrayDeque<int[]> stack = new ArrayDeque<>();
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 1) {
                    int size = 0;
                    grid[r][c] = id;
                    stack.push(new int[] {r, c});
                    while (!stack.isEmpty()) {
                        int[] p = stack.pop();
                        size++;
                        for (int d = 0; d < 4; d++) {
                            int nr = p[0] + dr[d], nc = p[1] + dc[d];
                            if (nr >= 0 && nc >= 0 && nr < n && nc < n && grid[nr][nc] == 1) {
                                grid[nr][nc] = id;
                                stack.push(new int[] {nr, nc});
                            }
                        }
                    }
                    area.put(id++, size);
                    best = Math.max(best, size);
                }
            }
        }
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 0) {
                    Set<Integer> neighbors = new HashSet<>();
                    int size = 1;
                    for (int d = 0; d < 4; d++) {
                        int nr = r + dr[d], nc = c + dc[d];
                        if (nr >= 0
                                && nc >= 0
                                && nr < n
                                && nc < n
                                && grid[nr][nc] > 1
                                && neighbors.add(grid[nr][nc])) {
                            size += area.get(grid[nr][nc]);
                        }
                    }
                    best = Math.max(best, size);
                }
            }
        }
        return best;
    }
}
