// Local checks for LeetCode 506.
import java.util.*;
class Case {
    public static int run() {
        Solution s = new Solution();
        Checks.eq(new String[]{"Gold Medal","Silver Medal","Bronze Medal","4","5"},s.findRelativeRanks(new int[]{5,4,3,2,1}));
        Checks.eq(new String[]{"Gold Medal","5","Bronze Medal","Silver Medal","4"},s.findRelativeRanks(new int[]{10,3,8,9,4}));
        return Checks.count;
    }
}

