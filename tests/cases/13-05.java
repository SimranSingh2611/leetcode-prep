// Local checks for LeetCode 230.
import java.util.*;
class Case {
    public static int run() {
        Checks.eq(1,new Solution().kthSmallest(Checks.tree(3,1,4,null,2),1));Checks.eq(3,new Solution().kthSmallest(Checks.tree(5,3,6,2,4,null,null,1),3));
        return Checks.count;
    }
}

