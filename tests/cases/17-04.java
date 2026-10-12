// Local checks for LeetCode 720.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        Checks.eq("world",s.longestWord(new String[]{"w","wo","wor","worl","world"}));
        Checks.eq("apple",s.longestWord(new String[]{"a","banana","app","appl","ap","apply","apple"}));
        Checks.eq("",s.longestWord(new String[]{"abc","ab"}));
        return Checks.count;
    }
}

