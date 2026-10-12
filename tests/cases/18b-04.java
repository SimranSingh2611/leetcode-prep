// Local checks for LeetCode 200.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        Checks.eq(3,s.numIslands(new char[][]{"11000".toCharArray(),"11000".toCharArray(),"00100".toCharArray(),"00011".toCharArray()}));
        Checks.eq(1,s.numIslands(new char[][]{{'1'}})); Checks.eq(0,s.numIslands(new char[][]{{'0'}}));
        for (int mask = 0; mask < 512; mask++) {
            char[][] a = new char[3][3], b = new char[3][3];
            for (int i = 0; i < 9; i++) { a[i/3][i%3] = b[i/3][i%3] = ((mask >>> i) & 1) == 1 ? '1' : '0'; }
            Checks.eq(new BfsSolution().numIslands(b), s.numIslands(a));
        }
        return Checks.count;
    }
}
