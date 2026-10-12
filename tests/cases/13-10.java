// Local checks for LeetCode 1569.
import java.util.*;
class Case {
    public static int run() {
        Checks.eq(1,new Solution().numOfWays(new int[]{2,1,3}));Checks.eq(5,new Solution().numOfWays(new int[]{3,4,5,1,2}));Checks.eq(0,new Solution().numOfWays(new int[]{1,2,3}));
        return Checks.count;
    }
}

