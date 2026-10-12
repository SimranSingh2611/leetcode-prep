// Local checks for LeetCode 802.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        Checks.eq(Arrays.asList(2,4,5,6),s.eventualSafeNodes(new int[][]{{1,2},{2,3},{5},{0},{5},{},{}}));
        Checks.eq(Collections.emptyList(),s.eventualSafeNodes(new int[][]{{0}}));
        Checks.eq(Arrays.asList(0,1),s.eventualSafeNodes(new int[][]{{1},{}}));
        Checks.eq(Arrays.asList(2),s.eventualSafeNodes(new int[][]{{1,2},{1},{}}));
        return Checks.count;
    }
}

