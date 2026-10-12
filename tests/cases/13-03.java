// Local checks for LeetCode 108.
import java.util.*;
class Case {
    public static int run() {
        Checks.eq(Arrays.asList(-10,-3,0,5,9),Checks.inorder(new Solution().sortedArrayToBST(new int[]{-10,-3,0,5,9})));Checks.eq(null,new Solution().sortedArrayToBST(new int[]{}));
        return Checks.count;
    }
}

