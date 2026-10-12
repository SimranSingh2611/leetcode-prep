// Local checks for LeetCode 547.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        Checks.eq(2,s.findCircleNum(new int[][]{{1,1,0},{1,1,0},{0,0,1}}));
        Checks.eq(3,s.findCircleNum(new int[][]{{1,0,0},{0,1,0},{0,0,1}}));
        Checks.eq(1,s.findCircleNum(new int[][]{{1}}));
        return Checks.count;
    }
}

