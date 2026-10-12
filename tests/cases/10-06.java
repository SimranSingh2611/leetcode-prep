// Local checks for LeetCode 2.
import java.util.*;
class Case {
    public static int run() {
        Checks.eq(new int[]{7,0,8},Checks.values(new Solution().addTwoNumbers(Checks.list(2,4,3),Checks.list(5,6,4))));
        Checks.eq(new int[]{0,0,1},Checks.values(new Solution().addTwoNumbers(Checks.list(9,9),Checks.list(1))));
        return Checks.count;
    }
}

