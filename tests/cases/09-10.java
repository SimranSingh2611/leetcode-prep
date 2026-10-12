// Local checks for LeetCode 503.
import java.util.*;
class Case {
    public static int run() {
        Checks.eq(new int[]{2,-1,2},new Solution().nextGreaterElements(new int[]{1,2,1}));
        Checks.eq(new int[]{-1,-1},new Solution().nextGreaterElements(new int[]{3,3}));
        return Checks.count;
    }
}

