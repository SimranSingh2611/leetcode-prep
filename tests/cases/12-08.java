// Local checks for LeetCode 1448.
import java.util.*;
class Case {
    public static int run() {
        Checks.eq(4,new Solution().goodNodes(Checks.tree(3,1,4,3,null,1,5)));Checks.eq(1,new Solution().goodNodes(new TreeNode(-5)));
        return Checks.count;
    }
}

