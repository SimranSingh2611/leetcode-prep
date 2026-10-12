// Local checks for LeetCode 455.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        Checks.eq(1,s.findContentChildren(new int[]{1,2,3},new int[]{1,1}));
        Checks.eq(2,s.findContentChildren(new int[]{1,2},new int[]{1,2,3}));
        Checks.eq(0,s.findContentChildren(new int[]{1},new int[]{}));
        return Checks.count;
    }
}

