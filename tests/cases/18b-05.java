// Local checks for LeetCode 695.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        Checks.eq(4,s.maxAreaOfIsland(new int[][]{{1,1,0},{0,1,1},{1,0,0}}));
        Checks.eq(0,s.maxAreaOfIsland(new int[][]{{0,0}}));
        int[][] line=new int[1][20000]; Arrays.fill(line[0],1); Checks.eq(20000,s.maxAreaOfIsland(line));
        return Checks.count;
    }
}
