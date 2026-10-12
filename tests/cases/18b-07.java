// Local checks for LeetCode 130.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        char[][] b={"XXXX".toCharArray(),"XOOX".toCharArray(),"XXOX".toCharArray(),"XOXX".toCharArray()};
        s.solve(b); Checks.eq(new char[][]{"XXXX".toCharArray(),"XXXX".toCharArray(),"XXXX".toCharArray(),"XOXX".toCharArray()},b);
        char[][] one={{'O'}}; s.solve(one); Checks.eq(new char[][]{{'O'}},one);
        for (int mask = 0; mask < 512; mask++) {
            char[][] a = new char[3][3], other = new char[3][3];
            for (int i = 0; i < 9; i++) { a[i/3][i%3] = other[i/3][i%3] = ((mask >>> i) & 1) == 1 ? 'O' : 'X'; }
            s.solve(a); new BfsSolution().solve(other); Checks.eq(other,a);
        }
        return Checks.count;
    }
}
