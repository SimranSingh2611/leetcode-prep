// Local checks for LeetCode 207.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        Checks.eq(true,s.canFinish(2,new int[][]{{1,0}}));
        Checks.eq(false,s.canFinish(2,new int[][]{{1,0},{0,1}}));
        Checks.eq(true,s.canFinish(4,new int[][]{}));
        Checks.eq(false,s.canFinish(3,new int[][]{{1,1}}));
        return Checks.count;
    }
}

