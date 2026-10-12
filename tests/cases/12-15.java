// Local checks for LeetCode 112: leaf-only acceptance and negative values.
class Case {
    public static int run() {
        Solution s = new Solution();
        Checks.eq(true, s.hasPathSum(Checks.tree(5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1), 22));
        Checks.eq(false, s.hasPathSum(null, 0));
        Checks.eq(false, s.hasPathSum(Checks.tree(1, 2), 1));
        Checks.eq(false, s.hasPathSum(Checks.tree(1, 2, 3), 5));
        Checks.eq(true, s.hasPathSum(Checks.tree(1, -2), -1));
        Checks.eq(true, s.hasPathSum(new TreeNode(0), 0));
        return Checks.count;
    }
}
