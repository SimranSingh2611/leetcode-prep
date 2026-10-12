// Local checks for LeetCode 135.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        Checks.eq(5,s.candy(new int[]{1,0,2})); Checks.eq(4,s.candy(new int[]{1,2,2}));
        Checks.eq(11,s.candy(new int[]{1,3,4,5,2})); Checks.eq(1,s.candy(new int[]{9}));
        Checks.eq(15,s.candy(new int[]{5,4,3,2,1}));
        return Checks.count;
    }
}

