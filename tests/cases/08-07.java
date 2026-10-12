// Local checks for LeetCode 394.
import java.util.*;
class Case {
    public static int run() {
        Checks.eq("accaccacc",new Solution().decodeString("3[a2[c]]"));
        Checks.eq("abccdcdcdxyz",new Solution().decodeString("abc3[cd]xyz"));
        return Checks.count;
    }
}

