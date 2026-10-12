// Local checks for LeetCode 199.
import java.util.*;
class Case {
    public static int run() {
        Checks.eq(Arrays.asList(1,3,4),new Solution().rightSideView(Checks.tree(1,2,3,null,5,null,4)));
        Checks.eq(Arrays.asList(1,2,3),new Solution().rightSideView(Checks.tree(1,2,null,3)));
        return Checks.count;
    }
}

