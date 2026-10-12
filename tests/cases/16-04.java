// Local checks for LeetCode 55.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        Checks.eq(true,s.canJump(new int[]{2,3,1,1,4}));
        Checks.eq(false,s.canJump(new int[]{3,2,1,0,4}));
        Checks.eq(true,s.canJump(new int[]{0}));
        Checks.eq(false,s.canJump(new int[]{0,1}));
        return Checks.count;
    }
}

