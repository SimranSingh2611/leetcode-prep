// Local checks for LeetCode 1047.
import java.util.*;
class Case {
    public static int run() {
        Checks.eq("ca", new Solution().removeDuplicates("abbaca"));
        Checks.eq("", new Solution().removeDuplicates("abba"));
        return Checks.count;
    }
}

