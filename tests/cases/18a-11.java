import java.util.*;
class Case {
    public static int run() {
        Solution s = new Solution();
        TreeNode root = Checks.tree(3,5,1,6,2,0,8,null,null,7,4);
        Checks.eq(new HashSet<>(Arrays.asList(7,4,1)), new HashSet<>(s.distanceK(root, root.left, 2)));
        Checks.eq(Arrays.asList(5), s.distanceK(root, root.left, 0));
        Checks.eq(Collections.emptyList(), s.distanceK(root, root.left, 20));
        return Checks.count;
    }
}
