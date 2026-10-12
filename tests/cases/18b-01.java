// Local checks for LeetCode 733.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution(); int[][] a={{1,1,1},{1,1,0},{1,0,1}};
        Checks.eq(new int[][]{{2,2,2},{2,2,0},{2,0,1}},s.floodFill(a,1,1,2));
        int[][] same={{0,0},{0,0}}; Checks.eq(true,s.floodFill(same,0,0,0)==same);
        Checks.eq(new int[][]{{3,3},{3,0}}, new BfsSolution().floodFill(new int[][]{{1,1},{1,0}},0,0,3));
        return Checks.count;
    }
}
