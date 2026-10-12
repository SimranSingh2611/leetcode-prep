// Local checks for LeetCode 1823.
import java.util.*;
class Case {
    public static int run() {
        Checks.eq(3,new Solution().findTheWinner(5,2));Checks.eq(1,new Solution().findTheWinner(6,5));
        for(int n=1;n<=50;n++)for(int k=1;k<=10;k++)Checks.eq(new Solution().findTheWinner(n,k),new IterativeSolution().findTheWinner(n,k));
        return Checks.count;
    }
}

