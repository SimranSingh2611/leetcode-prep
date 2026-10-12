// Local checks for LeetCode 662: virtual gaps, deep chains, and large widths.
class Case {
    public static int run() {
        Solution s = new Solution();
        Checks.eq(4, s.widthOfBinaryTree(Checks.tree(1, 3, 2, 5, 3, null, 9)));
        Checks.eq(7, s.widthOfBinaryTree(Checks.tree(1, 3, 2, 5, null, null, 9, 6, null, 7)));
        Checks.eq(0, s.widthOfBinaryTree(null));
        Checks.eq(1, s.widthOfBinaryTree(new TreeNode(0)));
        TreeNode chain = new TreeNode(0), tail = chain;
        for (int i = 1; i < 3000; i++) {
            tail.right = new TreeNode(i);
            tail = tail.right;
        }
        Checks.eq(1, s.widthOfBinaryTree(chain));
        // A deep shared stem followed by a fork exposes unnormalized overflow.
        TreeNode stem = new TreeNode(0), fork = stem;
        for (int i = 0; i < 100; i++) {
            fork.right = new TreeNode(0);
            fork = fork.right;
        }
        TreeNode left = fork, right = fork;
        for (int i = 0; i < 30; i++) {
            left.left = new TreeNode(0); left = left.left;
            right.right = new TreeNode(0); right = right.right;
        }
        Checks.eq(1 << 30, s.widthOfBinaryTree(stem));
        // Width 2^31 - 1 fits int but computing child indices needs long.
        left.left = new TreeNode(0);
        right.left = new TreeNode(0);
        Checks.eq(Integer.MAX_VALUE, s.widthOfBinaryTree(stem));
        return Checks.count;
    }
}
