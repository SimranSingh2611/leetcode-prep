// Local checks for LeetCode 947.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        Checks.eq(5,s.removeStones(new int[][]{{0,0},{0,1},{1,0},{1,2},{2,1},{2,2}}));
        Checks.eq(3,s.removeStones(new int[][]{{0,0},{0,2},{1,1},{2,0},{2,2}}));
        Checks.eq(0,s.removeStones(new int[][]{{0,0}}));
        Checks.eq(0,s.removeStones(new int[][]{{0,1},{1,0}}));
        return Checks.count;
    }
}

