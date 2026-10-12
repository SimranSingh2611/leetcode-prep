// Local checks for LeetCode 51.
import java.util.*;
class Case {
    public static int run() {
        Solution s = new Solution();
        Checks.eq(1,s.solveNQueens(1).size()); Checks.eq(0,s.solveNQueens(2).size());
        Checks.eq(2,s.solveNQueens(4).size()); Checks.eq(92,s.solveNQueens(8).size());
        for (List<String> b : s.solveNQueens(5)) {
            Set<Integer> cols = new HashSet<>(), d = new HashSet<>(), u = new HashSet<>();
            for (int r = 0; r < b.size(); r++) {
                int c = b.get(r).indexOf('Q');
                Checks.eq(true,c >= 0); Checks.eq(true,cols.add(c));
                Checks.eq(true,d.add(r-c)); Checks.eq(true,u.add(r+c));
            }
        }
        return Checks.count;
    }
}

