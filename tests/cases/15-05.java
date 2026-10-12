// Local checks for LeetCode 373.
import java.util.*;
class Case {
    public static int run() {
        Solution s = new Solution();
        Checks.eq(Arrays.asList(Arrays.asList(1,2),Arrays.asList(1,4),Arrays.asList(1,6)),
                  s.kSmallestPairs(new int[]{1,7,11},new int[]{2,4,6},3));
        Checks.eq(4,s.kSmallestPairs(new int[]{1,1},new int[]{1,2},10).size());
        Checks.eq(0,s.kSmallestPairs(new int[]{},new int[]{1},1).size());
        return Checks.count;
    }
}

