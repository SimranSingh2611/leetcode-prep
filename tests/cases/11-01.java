// Local checks for LeetCode 509.
import java.util.*;
class Case {
    public static int run() {
        for(int n=0;n<=20;n++){int a=new Solution().fib(n);Checks.eq(a,new IterativeSolution().fib(n));Checks.eq(a,new NaiveSolution().fib(n));}
        Checks.eq(832040,new Solution().fib(30));
        return Checks.count;
    }
}

