// Local checks for LeetCode 235.
import java.util.*;
class Case {
    public static int run() {
        TreeNode t=Checks.tree(6,2,8,0,4,7,9,null,null,3,5);Checks.eq(true,new Solution().lowestCommonAncestor(t,t.left,t.right)==t);Checks.eq(true,new Solution().lowestCommonAncestor(t,t.left,t.left.right)==t.left);
        return Checks.count;
    }
}

