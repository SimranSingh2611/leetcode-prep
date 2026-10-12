// Local checks for LeetCode 2192.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        Checks.eq(Arrays.asList(Collections.emptyList(),Arrays.asList(0),Arrays.asList(0,1),Arrays.asList(0,1,2)),
        s.getAncestors(4,new int[][]{{0,1},{1,2},{2,3},{0,3}}));
        Checks.eq(Arrays.asList(Collections.emptyList(),Collections.emptyList(),Arrays.asList(0,1)),
        s.getAncestors(3,new int[][]{{0,2},{1,2}}));
        return Checks.count;
    }
}

