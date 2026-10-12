// Local checks for LeetCode 71.
import java.util.*;
class Case {
    public static int run() {
        Checks.eq("/c",new Solution().simplifyPath("/a/./b/../../c/"));
        Checks.eq("/...",new Solution().simplifyPath("/../...//"));
        return Checks.count;
    }
}

