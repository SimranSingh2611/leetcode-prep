// Local checks for LeetCode 20.
import java.util.*;
class Case {
    public static int run() {
        Checks.eq(true, new Solution().isValid("([])"));
        Checks.eq(false, new Solution().isValid("([)]"));
        return Checks.count;
    }
}

