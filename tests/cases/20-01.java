// Local checks for LeetCode 1971.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        Checks.eq(true,s.validPath(3,new int[][]{{0,1},{1,2},{2,0}},0,2));
        Checks.eq(false,s.validPath(6,new int[][]{{0,1},{0,2},{3,5},{5,4},{4,3}},0,5));
        Checks.eq(true,s.validPath(1,new int[][]{},0,0));
        return Checks.count;
    }
}

