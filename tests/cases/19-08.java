// Local checks for LeetCode 2050.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        Checks.eq(8,s.minimumTime(3,new int[][]{{1,3},{2,3}},new int[]{3,2,5}));
        Checks.eq(12,s.minimumTime(5,new int[][]{{1,5},{2,5},{3,5},{3,4},{4,5}},new int[]{1,2,3,4,5}));
        Checks.eq(7,s.minimumTime(3,new int[][]{},new int[]{2,7,3}));
        return Checks.count;
    }
}

