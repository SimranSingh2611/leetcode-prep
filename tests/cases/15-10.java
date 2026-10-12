// Local checks for LeetCode 632.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        Checks.eq(new int[]{20,24},s.smallestRange(Arrays.asList(Arrays.asList(4,10,15,24,26),Arrays.asList(0,9,12,20),Arrays.asList(5,18,22,30))));
        Checks.eq(new int[]{1,1},s.smallestRange(Arrays.asList(Arrays.asList(1,2,3),Arrays.asList(1,2,3),Arrays.asList(1,2,3))));
        Checks.eq(new int[]{-5,-5},s.smallestRange(Arrays.asList(Arrays.asList(-5,0,8))));
        Checks.eq(new int[]{1,2},s.smallestRange(Arrays.asList(Arrays.asList(1,4),Arrays.asList(2,5))));
        return Checks.count;
    }
}

