// Local checks for LeetCode 402.
import java.util.*;
class Case {
    public static int run() {
        Checks.eq("1219",new Solution().removeKdigits("1432219",3));
        Checks.eq("200",new Solution().removeKdigits("10200",1));
        Checks.eq("0",new Solution().removeKdigits("10",2));
        return Checks.count;
    }
}

