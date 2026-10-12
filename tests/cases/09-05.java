// Local checks for LeetCode 496.
import java.util.*;
class Case {
    public static int run() {
        Checks.eq(new int[]{-1,3,-1},new Solution().nextGreaterElement(new int[]{4,1,2},new int[]{1,3,4,2}));
        return Checks.count;
    }
}

