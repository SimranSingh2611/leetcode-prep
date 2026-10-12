// Local checks for LeetCode 110: imbalance below an apparently balanced root.
class Case {
    public static int run() {
        Solution s = new Solution();
        Checks.eq(true, s.isBalanced(null));
        Checks.eq(true, s.isBalanced(new TreeNode(1)));
        Checks.eq(true, s.isBalanced(Checks.tree(3, 9, 20, null, null, 15, 7)));
        Checks.eq(false, s.isBalanced(Checks.tree(1, 2, 2, 3, 3, null, null, 4, 4)));
        TreeNode left = Checks.tree(1, 2, null, 3);
        TreeNode right = Checks.tree(1, null, 2, null, 3);
        Checks.eq(false, s.isBalanced(new TreeNode(0, left, right)));
        return Checks.count;
    }
}
