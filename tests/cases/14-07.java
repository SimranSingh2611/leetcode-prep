// Local checks for LeetCode 131.
import java.util.*;
class Case {
    public static int run() {
        Solution s = new Solution();
        Checks.eq(new HashSet<>(Arrays.asList(Arrays.asList("a","a","b"),Arrays.asList("aa","b"))),
                  new HashSet<>(s.partition("aab")));
        Checks.eq(Arrays.asList(Arrays.asList("a")), s.partition("a"));
        Checks.eq(4, s.partition("aaa").size());
        return Checks.count;
    }
}

