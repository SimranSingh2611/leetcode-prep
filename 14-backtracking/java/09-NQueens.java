// 51. N-Queens
// https://leetcode.com/problems/n-queens/
// Solution stores column choices and materializes boards only at leaves.
import java.util.*;

class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> out = new ArrayList<>();
        search(
                0,
                n,
                new int[n],
                new boolean[n],
                new boolean[2 * n - 1],
                new boolean[2 * n - 1],
                out);
        return out;
    }

    private void search(
            int r,
            int n,
            int[] position,
            boolean[] col,
            boolean[] down,
            boolean[] up,
            List<List<String>> out) {
        if (r == n) {
            List<String> board = new ArrayList<>();
            for (int c : position) {
                char[] row = new char[n];
                Arrays.fill(row, '.');
                row[c] = 'Q';
                board.add(new String(row));
            }
            out.add(board);
            return;
        }
        for (int c = 0; c < n; c++) {
            int d = r - c + n - 1, u = r + c;
            if (col[c] || down[d] || up[u]) {
                continue;
            }
            position[r] = c;
            col[c] = down[d] = up[u] = true;
            search(r + 1, n, position, col, down, up, out);
            col[c] = down[d] = up[u] = false;
        }
    }
}
