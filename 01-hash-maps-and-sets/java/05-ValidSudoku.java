/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd check each filled cell against its row, column, and box.
 * - A duplicate in any of those three groups invalidates the board; this checks existing
 *   entries without solving it.
 * Time complexity: O(1) for the fixed 9-by-9 board; O(n^2) for an n-by-n generalization.
 * Space complexity: O(1) for the fixed board; O(n^2) for generalized tracking sets.
 */

// 36. Valid Sudoku
// https://leetcode.com/problems/valid-sudoku/
// NeetCode 150: https://neetcode.io/problems/valid-sudoku/question?list=neetcode150
// O(81) time and O(81) space for the fixed 9-by-9 board.
class Solution {
    public boolean isValidSudoku(char[][] board) {
        boolean[][] rows = new boolean[9][9];
        boolean[][] columns = new boolean[9][9];
        boolean[][] boxes = new boolean[9][9];

        for (int row = 0; row < 9; row++) {
            for (int column = 0; column < 9; column++) {
                char cell = board[row][column];
                if (cell == '.') {
                    continue;
                }

                int digit = cell - '1';
                int box = (row / 3) * 3 + column / 3;
                if (rows[row][digit] || columns[column][digit] || boxes[box][digit]) {
                    return false;
                }
                rows[row][digit] = true;
                columns[column][digit] = true;
                boxes[box][digit] = true;
            }
        }
        return true;
    }
}

