// Local checks for LeetCode 1046.
import java.util.*;
class Case {
    public static int run() {
        Solution s = new Solution();
        Checks.eq(1,s.lastStoneWeight(new int[]{2,7,4,1,8,1}));
        Checks.eq(0,s.lastStoneWeight(new int[]{4,4})); Checks.eq(5,s.lastStoneWeight(new int[]{5}));
        return Checks.count;
    }
}

