// Local checks for LeetCode 739.
import java.util.*;
class Case {
    public static int run() {
        Checks.eq(new int[]{1,1,4,2,1,1,0,0},new Solution().dailyTemperatures(new int[]{73,74,75,71,69,72,76,73}));
        Checks.eq(new int[]{0,0},new Solution().dailyTemperatures(new int[]{70,70}));
        return Checks.count;
    }
}

