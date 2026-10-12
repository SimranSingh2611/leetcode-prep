// Local checks for LeetCode 1319.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        Checks.eq(1,s.makeConnected(4,new int[][]{{0,1},{0,2},{1,2}}));
        Checks.eq(2,s.makeConnected(6,new int[][]{{0,1},{0,2},{0,3},{1,2},{1,3}}));
        Checks.eq(-1,s.makeConnected(6,new int[][]{{0,1},{0,2},{0,3},{1,2}}));
        Checks.eq(0,s.makeConnected(1,new int[][]{}));
        return Checks.count;
    }
}

