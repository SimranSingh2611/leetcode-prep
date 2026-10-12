// Local checks for LeetCode 409.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        Checks.eq(7,s.longestPalindrome("abccccdd")); Checks.eq(1,s.longestPalindrome("a"));
        Checks.eq(1,s.longestPalindrome("Aa")); Checks.eq(4,s.longestPalindrome("aabb"));
        return Checks.count;
    }
}

