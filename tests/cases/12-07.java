// Local checks for LeetCode 236.
import java.util.*;
class Case {
    public static int run() {
        TreeNode t=Checks.tree(3,5,1,6,2,0,8,null,null,7,4);
        Checks.eq(true,new Solution().lowestCommonAncestor(t,t.left,t.right)==t);
        Checks.eq(true,new Solution().lowestCommonAncestor(t,t.left,t.left.right.right)==t.left);
        return Checks.count;
    }
}

