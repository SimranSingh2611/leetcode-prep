// Local checks for LeetCode 150.
import java.util.*;
class Case {
    public static int run() {
        Checks.eq(9,new Solution().evalRPN(new String[]{"2","1","+","3","*"}));
        Checks.eq(6,new Solution().evalRPN(new String[]{"4","13","5","/","+"}));
        return Checks.count;
    }
}

