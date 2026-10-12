// Local checks for LeetCode 124.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();Checks.eq(42,s.maxPathSum(Checks.tree(-10,9,20,null,null,15,7)));Checks.eq(-3,s.maxPathSum(new TreeNode(-3)));
        return Checks.count;
    }
}

