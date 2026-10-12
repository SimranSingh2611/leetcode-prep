// Local checks for LeetCode 779.
import java.util.*;
class Case {
    public static int run() {
        Checks.eq(0,new Solution().kthGrammar(1,1));Checks.eq(1,new Solution().kthGrammar(2,2));
        for(int k=1;k<=32;k++)Checks.eq(Integer.bitCount(k-1)%2,new Solution().kthGrammar(6,k));
        return Checks.count;
    }
}

