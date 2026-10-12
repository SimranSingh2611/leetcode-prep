// Local checks for LeetCode 46.
import java.util.*;
class Case {
    public static int run() {
        Solution s = new Solution();
        List<List<Integer>> out = s.permute(new int[]{1,2,3});
        Checks.eq(6, out.size()); Checks.eq(6, new HashSet<>(out).size());
        Checks.eq(true, out.contains(Arrays.asList(3,1,2)));
        Checks.eq(Arrays.asList(Arrays.asList(7)), s.permute(new int[]{7}));
        return Checks.count;
    }
}

