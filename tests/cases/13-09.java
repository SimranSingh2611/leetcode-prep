// Local checks for LeetCode 1373.
import java.util.*;
class Case {
    public static int run() {
        Checks.eq(20,new Solution().maxSumBST(Checks.tree(1,4,3,2,4,2,5,null,null,null,null,null,null,4,6)));
        Checks.eq(0,new Solution().maxSumBST(Checks.tree(-4,-2,-5)));
        return Checks.count;
    }
}

