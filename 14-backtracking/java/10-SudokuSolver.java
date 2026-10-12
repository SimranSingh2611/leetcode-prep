// 37. Sudoku Solver
// https://leetcode.com/problems/sudoku-solver/
// Solution fills board in place; assumes the problem's valid puzzle with a unique solution.
import java.util.*;

class Solution {
    public void solveSudoku(char[][] board) {
        int[] rows = new int[9], cols = new int[9], boxes = new int[9];
        List<int[]> empty = new ArrayList<>();
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                if (board[r][c] == '.') {
                    empty.add(new int[] {r, c});
                } else {
                    int bit = 1 << (board[r][c] - '1');
                    rows[r] |= bit;
                    cols[c] |= bit;
                    boxes[(r / 3) * 3 + c / 3] |= bit;
                }
            }
        }
        fill(board, empty, 0, rows, cols, boxes);
    }

    private boolean fill(
            char[][] b, List<int[]> empty, int at, int[] rows, int[] cols, int[] boxes) {
        if (at == empty.size()) {
            return true;
        }
        int best = at, fewest = 10;
        for (int i = at; i < empty.size(); i++) {
            int[] p = empty.get(i);
            int count = Integer.bitCount(allowed(p, rows, cols, boxes));
            if (count < fewest) {
                fewest = count;
                best = i;
            }
            if (count <= 1) {
                break;
            }
        }
        if (fewest == 0) {
            return false;
        }
        Collections.swap(empty, at, best);
        int[] p = empty.get(at);
        int r = p[0], c = p[1], box = (r / 3) * 3 + c / 3;
        int mask = allowed(p, rows, cols, boxes);
        while (mask != 0) {
            int bit = mask & -mask;
            mask -= bit;
            rows[r] |= bit;
            cols[c] |= bit;
            boxes[box] |= bit;
            b[r][c] = (char) ('1' + Integer.numberOfTrailingZeros(bit));
            if (fill(b, empty, at + 1, rows, cols, boxes)) {
                return true;
            }
            rows[r] ^= bit;
            cols[c] ^= bit;
            boxes[box] ^= bit;
            b[r][c] = '.';
        }
        Collections.swap(empty, at, best);
        return false;
    }

    private int allowed(int[] p, int[] rows, int[] cols, int[] boxes) {
        return 511 & ~(rows[p[0]] | cols[p[1]] | boxes[(p[0] / 3) * 3 + p[1] / 3]);
    }
}
