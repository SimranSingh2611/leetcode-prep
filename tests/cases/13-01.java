// Local checks for LeetCode 700.
import java.util.*;
class Case {
    public static int run() {
        TreeNode t=Checks.tree(4,2,7,1,3);Checks.eq(true,new Solution().searchBST(t,2)==t.left);Checks.eq(null,new Solution().searchBST(t,5));
        return Checks.count;
    }
}

