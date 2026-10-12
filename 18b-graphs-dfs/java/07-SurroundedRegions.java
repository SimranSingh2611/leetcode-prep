/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd mark the O cells reachable from the border as safe.
 * - Every remaining O is enclosed and can flip to X, then I'd restore the safe markers.
 * Time complexity: O(RC) for an R by C board.
 * Space complexity: O(RC) auxiliary DFS stack; temporary markers use the board itself.
 */

// 130. Surrounded Regions
// https://leetcode.com/problems/surrounded-regions/
// Solution intentionally modifies board in place and removes all temporary markers before
// returning.
import java.util.*;

class Solution {
    public void solve(char[][] board) {
        int rows = board.length, cols = board[0].length;
        ArrayDeque<int[]> stack = new ArrayDeque<>();
        for (int r = 0; r < rows; r++) {
            mark(board, r, 0, stack);
            mark(board, r, cols - 1, stack);
        }
        for (int c = 0; c < cols; c++) {
            mark(board, 0, c, stack);
            mark(board, rows - 1, c, stack);
        }
        int[] dr = {1, -1, 0, 0}, dc = {0, 0, 1, -1};
        while (!stack.isEmpty()) {
            int[] p = stack.pop();
            for (int d = 0; d < 4; d++) {
                mark(board, p[0] + dr[d], p[1] + dc[d], stack);
            }
        }
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (board[r][c] == 'O') {
                    board[r][c] = 'X';
                } else if (board[r][c] == '#') {
                    board[r][c] = 'O';
                }
            }
        }
    }

    private void mark(char[][] b, int r, int c, ArrayDeque<int[]> stack) {
        if (r >= 0 && c >= 0 && r < b.length && c < b[0].length && b[r][c] == 'O') {
            b[r][c] = '#';
            stack.push(new int[] {r, c});
        }
    }
}

// Retained breadth-first alternative for comparison; the primary Solution above uses DFS.
class BfsSolution {
    public void solve(char[][] board) {
        int rows = board.length, cols = board[0].length;
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        for (int r = 0; r < rows; r++) {
            mark(board, r, 0, queue);
            mark(board, r, cols - 1, queue);
        }
        for (int c = 0; c < cols; c++) {
            mark(board, 0, c, queue);
            mark(board, rows - 1, c, queue);
        }
        int[] dr = {1, -1, 0, 0}, dc = {0, 0, 1, -1};
        while (!queue.isEmpty()) {
            int[] p = queue.poll();
            for (int d = 0; d < 4; d++) {
                mark(board, p[0] + dr[d], p[1] + dc[d], queue);
            }
        }
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (board[r][c] == 'O') {
                    board[r][c] = 'X';
                } else if (board[r][c] == '#') {
                    board[r][c] = 'O';
                }
            }
        }
    }

    private void mark(char[][] b, int r, int c, ArrayDeque<int[]> queue) {
        if (r >= 0 && c >= 0 && r < b.length && c < b[0].length && b[r][c] == 'O') {
            b[r][c] = '#';
            queue.offer(new int[] {r, c});
        }
    }
}
