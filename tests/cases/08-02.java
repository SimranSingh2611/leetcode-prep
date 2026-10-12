// Local checks for LeetCode 682.
import java.util.*;
class Case {
    public static int run() {
        Checks.eq(30, new Solution().calPoints(new String[]{"5","2","C","D","+"}));
        Checks.eq(0, new Solution().calPoints(new String[]{"1","C"}));
        return Checks.count;
    }
}

