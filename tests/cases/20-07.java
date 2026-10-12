// Local checks for LeetCode 1061.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        Checks.eq("makkek",s.smallestEquivalentString("parker","morris","parser"));
        Checks.eq("hdld",s.smallestEquivalentString("hello","world","hold"));
        Checks.eq("aaaz",s.smallestEquivalentString("ab","bc","abcz"));
        return Checks.count;
    }
}

