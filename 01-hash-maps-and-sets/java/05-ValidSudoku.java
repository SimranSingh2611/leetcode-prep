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

