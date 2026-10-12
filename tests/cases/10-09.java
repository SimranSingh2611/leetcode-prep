// Local checks for LeetCode 25.
import java.util.*;
class Case {
    public static int run() {
        Checks.eq(new int[]{2,1,4,3,5},Checks.values(new Solution().reverseKGroup(Checks.list(1,2,3,4,5),2)));
        Checks.eq(new int[]{3,2,1,4,5},Checks.values(new Solution().reverseKGroup(Checks.list(1,2,3,4,5),3)));
        return Checks.count;
    }
}

