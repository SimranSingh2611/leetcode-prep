// Local checks for LeetCode 104.
import java.util.*;
class Case {
    public static int run() {
        TreeNode t=Checks.tree(3,9,20,null,null,15,7);Checks.eq(3,new Solution().maxDepth(t));Checks.eq(3,new BreadthFirstSolution().maxDepth(t));Checks.eq(0,new Solution().maxDepth(null));
        return Checks.count;
    }
}

