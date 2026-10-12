// Local checks for LeetCode 231.
import java.util.*;
class Case {
    public static int run() {
        for(int n=-2;n<200;n++)Checks.eq(new Solution().isPowerOfTwo(n),new BitwiseSolution().isPowerOfTwo(n));
        Checks.eq(true,new Solution().isPowerOfTwo(1<<30));
        return Checks.count;
    }
}

