// Local checks for LeetCode 98.
import java.util.*;
class Case {
    public static int run() {
        Checks.eq(true,new Solution().isValidBST(Checks.tree(2,1,3)));Checks.eq(false,new Solution().isValidBST(Checks.tree(5,1,4,null,null,3,6)));Checks.eq(true,new Solution().isValidBST(new TreeNode(Integer.MIN_VALUE)));
        return Checks.count;
    }
}

