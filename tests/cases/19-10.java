// Local checks for LeetCode 1857.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        Checks.eq(3,s.largestPathValue("abaca",new int[][]{{0,1},{0,2},{2,3},{3,4}}));
        Checks.eq(-1,s.largestPathValue("a",new int[][]{{0,0}}));
        Checks.eq(1,s.largestPathValue("abc",new int[][]{}));
        Checks.eq(-1,s.largestPathValue("abcd",new int[][]{{0,1},{2,3},{3,2}}));
        Checks.eq(3,s.largestPathValue("aaa",new int[][]{{0,1},{1,2},{0,2}}));
        return Checks.count;
    }
}

