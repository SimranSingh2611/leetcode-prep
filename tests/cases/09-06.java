// Local checks for LeetCode 1475.
import java.util.*;
class Case {
    public static int run() {
        Checks.eq(new int[]{4,2,4,2,3},new Solution().finalPrices(new int[]{8,4,6,2,3}));
        Checks.eq(new int[]{0,2},new Solution().finalPrices(new int[]{2,2}));
        return Checks.count;
    }
}

