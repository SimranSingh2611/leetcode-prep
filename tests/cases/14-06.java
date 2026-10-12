// Local checks for LeetCode 39.
import java.util.*;
class Case {
    public static int run() {
        Solution s = new Solution();
        Checks.eq(new HashSet<>(Arrays.asList(Arrays.asList(2,2,3),Arrays.asList(7))),
                  new HashSet<>(s.combinationSum(new int[]{2,3,6,7},7)));
        Checks.eq(0, s.combinationSum(new int[]{2},1).size());
        int[] a = {7,2,3}; s.combinationSum(a,7); Checks.eq(new int[]{7,2,3},a);
        return Checks.count;
    }
}

