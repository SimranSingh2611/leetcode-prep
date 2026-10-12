// Local checks for LeetCode 684.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        Checks.eq(new int[]{2,3},s.findRedundantConnection(new int[][]{{1,2},{1,3},{2,3}}));
        Checks.eq(new int[]{1,4},s.findRedundantConnection(new int[][]{{1,2},{2,3},{3,4},{1,4},{1,5}}));
        return Checks.count;
    }
}

