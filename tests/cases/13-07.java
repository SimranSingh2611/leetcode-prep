// Local checks for LeetCode 450.
import java.util.*;
class Case {
    public static int run() {
        Checks.eq(Arrays.asList(2,4,5,6,7),Checks.inorder(new Solution().deleteNode(Checks.tree(5,3,6,2,4,null,7),3)));Checks.eq(null,new Solution().deleteNode(new TreeNode(1),1));
        return Checks.count;
    }
}

