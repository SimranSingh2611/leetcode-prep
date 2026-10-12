// Local checks for LeetCode 101: mirrored null positions matter.
class Case {
    public static int run() {
        Solution s = new Solution();
        Checks.eq(true, s.isSymmetric(Checks.tree(1, 2, 2, 3, 4, 4, 3)));
        Checks.eq(false, s.isSymmetric(Checks.tree(1, 2, 2, null, 3, null, 3)));
        Checks.eq(true, s.isSymmetric(Checks.tree(1, 2, 2, null, 3, 3, null)));
        Checks.eq(false, s.isSymmetric(Checks.tree(1, 2, 3)));
        Checks.eq(true, s.isSymmetric(new TreeNode(1)));
        Checks.eq(true, s.isSymmetric(null));
        return Checks.count;
    }
}
