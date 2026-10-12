// Local checks for LeetCode 21.
import java.util.*;
class Case {
    public static int run() {
        Checks.eq(new int[]{1,1,2,3,4,4},Checks.values(new Solution().mergeTwoLists(Checks.list(1,2,4),Checks.list(1,3,4))));
        Checks.eq(null,new Solution().mergeTwoLists(null,null));
        return Checks.count;
    }
}

