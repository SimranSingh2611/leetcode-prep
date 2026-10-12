// Local checks for LeetCode 226.
import java.util.*;
class Case {
    public static int run() {
        TreeNode t=new Solution().invertTree(Checks.tree(4,2,7,1,3,6,9));Checks.eq(Arrays.asList(9,7,6,4,3,2,1),Checks.inorder(t));Checks.eq(null,new Solution().invertTree(null));
        return Checks.count;
    }
}

