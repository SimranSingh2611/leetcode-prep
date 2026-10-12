// Local checks for LeetCode 102.
import java.util.*;
class Case {
    public static int run() {
        Checks.eq(Arrays.asList(Arrays.asList(3),Arrays.asList(9,20),Arrays.asList(15,7)),new Solution().levelOrder(Checks.tree(3,9,20,null,null,15,7)));Checks.eq(Collections.emptyList(),new Solution().levelOrder(null));
        return Checks.count;
    }
}

