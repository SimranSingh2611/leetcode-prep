// Local checks for LeetCode 45.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        Checks.eq(2,s.jump(new int[]{2,3,1,1,4}));
        Checks.eq(2,s.jump(new int[]{2,3,0,1,4}));
        Checks.eq(0,s.jump(new int[]{0})); Checks.eq(3,s.jump(new int[]{1,1,1,1}));
        return Checks.count;
    }
}

