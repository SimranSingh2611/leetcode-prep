// Local checks for LeetCode 938.
import java.util.*;
class Case {
    public static int run() {
        Checks.eq(32,new Solution().rangeSumBST(Checks.tree(10,5,15,3,7,null,18),7,15));Checks.eq(0,new Solution().rangeSumBST(null,1,3));
        return Checks.count;
    }
}

