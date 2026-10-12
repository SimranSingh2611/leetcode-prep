import java.util.*;
class Case {
    public static int run() {
        Solution s = new Solution();
        Checks.eq(4, s.amountOfTime(Checks.tree(1,5,3,null,4,10,6,9,2), 3));
        Checks.eq(0, s.amountOfTime(new TreeNode(1), 1));
        TreeNode root = new TreeNode(0), tail = root;
        for (int i = 1; i < 20000; i++) { tail.right = new TreeNode(i); tail = tail.right; }
        Checks.eq(19999, s.amountOfTime(root, 0));
        return Checks.count;
    }
}
