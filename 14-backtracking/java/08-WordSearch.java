/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd try matching paths from each possible starting cell using DFS.
 * - I'd mark each cell only for the current path and restore it on return, since cells may be
 *   reused by other candidate paths.
 * Time complexity: O(RC 3^L) loose worst-case bound for R rows, C columns and word length L;
 *   the first step allows four directions.
 * Space complexity: O(min(L, RC)) recursive stack; the board provides visited markers.
 */

// 79. Word Search
// https://leetcode.com/problems/word-search/
// Solution temporarily mutates board but restores every cell before returning.
import java.util.*;

class Solution {
    public boolean exist(char[][] board, String word) {
        if (word.length() > board.length * board[0].length) {
            return false;
        }
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                if (dfs(board, word, r, c, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] b, String w, int r, int c, int i) {
        if (i == w.length()) {
            return true;
        }
        if (r < 0 || c < 0 || r == b.length || c == b[0].length || b[r][c] != w.charAt(i)) {
            return false;
        }
        char saved = b[r][c];
        b[r][c] = '#';
        boolean found =
                dfs(b, w, r + 1, c, i + 1)
                        || dfs(b, w, r - 1, c, i + 1)
                        || dfs(b, w, r, c + 1, i + 1)
                        || dfs(b, w, r, c - 1, i + 1);
        b[r][c] = saved;
        return found;
    }
}
