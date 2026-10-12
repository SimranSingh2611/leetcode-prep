// Local checks for LeetCode 994.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        Checks.eq(4,s.orangesRotting(new int[][]{{2,1,1},{1,1,0},{0,1,1}}));
        Checks.eq(-1,s.orangesRotting(new int[][]{{2,1,1},{0,1,1},{1,0,1}}));
        Checks.eq(0,s.orangesRotting(new int[][]{{0,2}}));
        Checks.eq(1,s.orangesRotting(new int[][]{{2,1,2}}));
        return Checks.count;
    }
}
