// Local checks for LeetCode 79.
import java.util.*;
class Case {
    public static int run() {
        Solution s = new Solution();
        char[][] b = {"ABCE".toCharArray(),"SFCS".toCharArray(),"ADEE".toCharArray()};
        char[][] before = {"ABCE".toCharArray(),"SFCS".toCharArray(),"ADEE".toCharArray()};
        Checks.eq(true,s.exist(b,"ABCCED")); Checks.eq(before,b);
        Checks.eq(true,s.exist(b,"SEE")); Checks.eq(before,b);
        Checks.eq(false,s.exist(b,"ABCB")); Checks.eq(before,b);
        Checks.eq(false,s.exist(new char[][]{{'A'}},"AA"));
        return Checks.count;
    }
}

