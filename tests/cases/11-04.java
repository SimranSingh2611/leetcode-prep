// Local checks for LeetCode 50.
import java.util.*;
class Case {
    public static int run() {
        Checks.close(1024,new Solution().myPow(2,10));Checks.close(0.25,new Solution().myPow(2,-2));
        Checks.close(1,new Solution().myPow(-1,Integer.MIN_VALUE));
        for(int n=-20;n<=20;n++)Checks.close(new Solution().myPow(1.5,n),new IterativeSolution().myPow(1.5,n));
        return Checks.count;
    }
}

