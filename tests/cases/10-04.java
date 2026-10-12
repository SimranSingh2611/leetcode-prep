// Local checks for LeetCode 19.
import java.util.*;
class Case {
    public static int run() {
        Checks.eq(new int[]{1,2,3,5},Checks.values(new Solution().removeNthFromEnd(Checks.list(1,2,3,4,5),2)));
        Checks.eq(null,new Solution().removeNthFromEnd(Checks.list(1),1));
        return Checks.count;
    }
}

