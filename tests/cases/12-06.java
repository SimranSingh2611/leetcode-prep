// Local checks for LeetCode 105.
import java.util.*;
class Case {
    public static int run() {
        TreeNode t=new Solution().buildTree(new int[]{3,9,20,15,7},new int[]{9,3,15,20,7});
        Checks.eq(3,t.val);Checks.eq(20,t.right.val);Checks.eq(Arrays.asList(9,3,15,20,7),Checks.inorder(t));
        return Checks.count;
    }
}

