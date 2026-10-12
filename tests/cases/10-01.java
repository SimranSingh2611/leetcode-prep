// Local checks for LeetCode 206.
import java.util.*;
class Case {
    public static int run() {
        Checks.eq(new int[]{3,2,1},Checks.values(new Solution().reverseList(Checks.list(1,2,3))));
        Checks.eq(new int[]{2,1},Checks.values(new RecursiveSolution().reverseList(Checks.list(1,2))));
        Checks.eq(null,new Solution().reverseList(null));
        return Checks.count;
    }
}

