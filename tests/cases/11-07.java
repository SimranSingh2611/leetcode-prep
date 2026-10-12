// Local checks for LeetCode 390.
import java.util.*;
class Case {
    public static int run() {
        Checks.eq(6,new Solution().lastRemaining(9));
        for(int n=1;n<=200;n++)Checks.eq(new Solution().lastRemaining(n),new IterativeSolution().lastRemaining(n));
        return Checks.count;
    }
}

