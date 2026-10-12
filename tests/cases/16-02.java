// Local checks for LeetCode 860.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        Checks.eq(true,s.lemonadeChange(new int[]{5,5,5,10,20}));
        Checks.eq(false,s.lemonadeChange(new int[]{5,5,10,10,20}));
        Checks.eq(false,s.lemonadeChange(new int[]{10}));
        Checks.eq(true,s.lemonadeChange(new int[]{5,5,5,5,10,20,10}));
        return Checks.count;
    }
}

