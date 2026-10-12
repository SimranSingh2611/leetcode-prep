// Local checks for LeetCode 827.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        Checks.eq(3,s.largestIsland(new int[][]{{1,0},{0,1}}));
        Checks.eq(4,s.largestIsland(new int[][]{{1,1},{1,0}}));
        Checks.eq(4,s.largestIsland(new int[][]{{1,1},{1,1}}));
        Checks.eq(1,s.largestIsland(new int[][]{{0,0},{0,0}}));
        Checks.eq(9,s.largestIsland(new int[][]{{1,1,1},{1,0,1},{1,1,1}}));
        int[][] full=new int[200][200]; for (int[] row:full) Arrays.fill(row,1);
        Checks.eq(40000,s.largestIsland(full));
        return Checks.count;
    }
}
