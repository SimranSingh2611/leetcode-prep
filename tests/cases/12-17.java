// Local checks for LeetCode 113: output ownership, backtracking, and leaf rules.
import java.util.*;
class Case {
    public static int run() {
        Solution s = new Solution();
        Checks.eq(Arrays.asList(Arrays.asList(5, 4, 11, 2), Arrays.asList(5, 8, 4, 5)),
            s.pathSum(Checks.tree(5, 4, 8, 11, null, 13, 4, 7, 2, null, null, 5, 1), 22));
        Checks.eq(Collections.emptyList(), s.pathSum(null, 0));
        Checks.eq(Collections.emptyList(), s.pathSum(Checks.tree(1, 2), 1));
        Checks.eq(Arrays.asList(Arrays.asList(1, -2)), s.pathSum(Checks.tree(1, -2), -1));
        TreeNode root = Checks.tree(0, 0, 0);
        List<List<Integer>> paths = s.pathSum(root, 0);
        Checks.eq(Arrays.asList(Arrays.asList(0, 0), Arrays.asList(0, 0)), paths);
        paths.get(0).set(0, 99);
        Checks.eq(Arrays.asList(0, 0), paths.get(1));
        Checks.eq(0, root.val);
        Checks.eq(Arrays.asList(Arrays.asList(0)), s.pathSum(new TreeNode(0), 0));
        return Checks.count;
    }
}
