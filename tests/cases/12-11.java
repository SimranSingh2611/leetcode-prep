// Local checks for LeetCode 543: edges, off-root diameter, and repeated calls.
class Case {
    public static int run() {
        Solution s = new Solution();
        Checks.eq(3, s.diameterOfBinaryTree(Checks.tree(1, 2, 3, 4, 5)));
        TreeNode branch = Checks.tree(2, 3, 4, 5, null, null, 6, 7, null, null, 8);
        Checks.eq(6, s.diameterOfBinaryTree(new TreeNode(1, branch, null)));
        Checks.eq(0, s.diameterOfBinaryTree(new TreeNode(9)));
        Checks.eq(0, s.diameterOfBinaryTree(null));
        Checks.eq(2, s.diameterOfBinaryTree(Checks.tree(1, null, 2, null, 3)));
        return Checks.count;
    }
}
