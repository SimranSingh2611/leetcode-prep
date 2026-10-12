// Local checks for LeetCode 326.
import java.util.*;
class Case {
    public static int run() {
        Checks.eq(true,new Solution().isPowerOfThree(27));Checks.eq(false,new Solution().isPowerOfThree(45));
        for(int n=0;n<200;n++)Checks.eq(new Solution().isPowerOfThree(n),new IterativeSolution().isPowerOfThree(n));
        return Checks.count;
    }
}

