// Local checks for LeetCode 572: exact shape, duplicate values, whole-tree match.
class Case {
    public static int run() {
        Solution s = new Solution();
        TreeNode sub = Checks.tree(4, 1, 2);
        Checks.eq(true, s.isSubtree(Checks.tree(3, 4, 5, 1, 2), sub));
        Checks.eq(false, s.isSubtree(Checks.tree(3, 4, 5, 1, 2, null, null, null, null, 0), sub));
        Checks.eq(true, s.isSubtree(sub, Checks.tree(4, 1, 2)));
        Checks.eq(false, s.isSubtree(Checks.tree(1, 2), Checks.tree(1, null, 2)));
        Checks.eq(true, s.isSubtree(Checks.tree(1, 1, null, 1), Checks.tree(1, 1)));
        Checks.eq(false, s.isSubtree(null, sub));
        Checks.eq(true, s.isSubtree(sub, null));
        return Checks.count;
    }
}
