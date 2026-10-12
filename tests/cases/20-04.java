// Local checks for LeetCode 990.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        Checks.eq(false,s.equationsPossible(new String[]{"a==b","b!=a"}));
        Checks.eq(true,s.equationsPossible(new String[]{"b==a","a==b"}));
        Checks.eq(false,s.equationsPossible(new String[]{"a!=c","a==b","b==c"}));
        Checks.eq(false,s.equationsPossible(new String[]{"a!=a"}));
        return Checks.count;
    }
}

