// Local checks for LeetCode 37.
import java.util.*;
class Case {
    public static int run() {
        Solution s = new Solution();
        String[] rows = {"53..7....","6..195...",".98....6.","8...6...3","4..8.3..1","7...2...6",".6....28.","...419..5","....8..79"};
        String[] solved = {"534678912","672195348","198342567","859761423","426853791","713924856","961537284","287419635","345286179"};
        char[][] b = new char[9][];
        for (int i = 0; i < 9; i++) b[i] = rows[i].toCharArray();
        s.solveSudoku(b);
        for (int i = 0; i < 9; i++) Checks.eq(solved[i],new String(b[i]));
        s.solveSudoku(b);
        for (int i = 0; i < 9; i++) Checks.eq(solved[i],new String(b[i]));
        return Checks.count;
    }
}

