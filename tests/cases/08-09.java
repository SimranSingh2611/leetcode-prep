// Local checks for LeetCode 224.
import java.util.*;
class Case {
    public static int run() {
        Checks.eq(23,new Solution().calculate("(1+(4+5+2)-3)+(6+8)"));
        Checks.eq(-4,new Solution().calculate("-(2 + 3) + 1"));
        Checks.eq(3,new Solution().calculate("1-(-2)"));
        return Checks.count;
    }
}

