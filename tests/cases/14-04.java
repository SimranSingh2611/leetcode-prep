// Local checks for LeetCode 78.
import java.util.*;
class Case {
    public static int run() {
        Solution s = new Solution();
        List<List<Integer>> out = s.subsets(new int[]{1,2,3});
        Checks.eq(8, out.size()); Checks.eq(8, new HashSet<>(out).size());
        Checks.eq(true, out.contains(Arrays.asList(1,3)));
        Checks.eq(Arrays.asList(Collections.emptyList()), s.subsets(new int[]{}));
        return Checks.count;
    }
}

