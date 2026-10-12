// Local checks for LeetCode 100.
import java.util.*;
class Case {
    public static int run() {
        Checks.eq(true,new Solution().isSameTree(Checks.tree(1,2,3),Checks.tree(1,2,3)));
        Checks.eq(false,new Solution().isSameTree(Checks.tree(1,2),Checks.tree(1,null,2)));
        return Checks.count;
    }
}

