// Local checks for LeetCode 103: sparse and wide alternating levels.
import java.util.*;
class Case {
    public static int run() {
        Solution s = new Solution();
        Checks.eq(Arrays.asList(Arrays.asList(3), Arrays.asList(20, 9), Arrays.asList(15, 7)),
            s.zigzagLevelOrder(Checks.tree(3, 9, 20, null, null, 15, 7)));
        Checks.eq(Arrays.asList(Arrays.asList(1), Arrays.asList(3, 2), Arrays.asList(4, 5, 6, 7),
                Arrays.asList(9, 8)),
            s.zigzagLevelOrder(Checks.tree(1, 2, 3, 4, 5, 6, 7, 8, 9)));
        Checks.eq(Collections.emptyList(), s.zigzagLevelOrder(null));
        Checks.eq(Arrays.asList(Arrays.asList(1), Arrays.asList(2), Arrays.asList(3)),
            s.zigzagLevelOrder(Checks.tree(1, null, 2, 3)));
        return Checks.count;
    }
}
