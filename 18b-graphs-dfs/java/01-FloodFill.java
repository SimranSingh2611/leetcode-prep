/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd explore the starting cell's same-color component using an explicit DFS stack.
 * - Recoloring on push prevents duplicate scheduling; an unchanged replacement color needs an
 *   immediate return.
 * Time complexity: O(RC) worst case for an R by C image.
 * Space complexity: O(RC) auxiliary DFS stack; recolors the image in place.
 */

// 733. Flood Fill
// https://leetcode.com/problems/flood-fill/
// Solution mutates image in place and returns the same array reference.
import java.util.*;

class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int original = image[sr][sc];
        if (original == color) {
            return image;
        }
        int rows = image.length, cols = image[0].length;
        int[] dr = {1, -1, 0, 0}, dc = {0, 0, 1, -1};
        ArrayDeque<int[]> stack = new ArrayDeque<>();
        image[sr][sc] = color;
        stack.push(new int[] {sr, sc});
        while (!stack.isEmpty()) {
            int[] p = stack.pop();
            for (int d = 0; d < 4; d++) {
                int r = p[0] + dr[d], c = p[1] + dc[d];
                if (r >= 0 && c >= 0 && r < rows && c < cols && image[r][c] == original) {
                    image[r][c] = color;
                    stack.push(new int[] {r, c});
                }
            }
        }
        return image;
    }
}

// Retained breadth-first alternative for comparison; the primary Solution above uses DFS.
class BfsSolution {
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int original = image[sr][sc];
        if (original == color) {
            return image;
        }
        int rows = image.length, cols = image[0].length;
        int[] dr = {1, -1, 0, 0}, dc = {0, 0, 1, -1};
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        image[sr][sc] = color;
        queue.offer(new int[] {sr, sc});
        while (!queue.isEmpty()) {
            int[] p = queue.poll();
            for (int d = 0; d < 4; d++) {
                int r = p[0] + dr[d], c = p[1] + dc[d];
                if (r >= 0 && c >= 0 && r < rows && c < cols && image[r][c] == original) {
                    image[r][c] = color;
                    queue.offer(new int[] {r, c});
                }
            }
        }
        return image;
    }
}
