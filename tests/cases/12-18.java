// Local checks for LeetCode 437; independent enumeration of every path start.
import java.util.*;
class Case {
    private static int from(TreeNode node, long remaining) {
        if (node == null) return 0;
        return (node.val == remaining ? 1 : 0)
            + from(node.left, remaining - node.val) + from(node.right, remaining - node.val);
    }
    private static int brute(TreeNode node, int target) {
        if (node == null) return 0;
        return from(node, target) + brute(node.left, target) + brute(node.right, target);
    }
    private static TreeNode randomTree(Random random, int depth) {
        if (depth == 0 || random.nextInt(4) == 0) return null;
        return new TreeNode(random.nextInt(7) - 3,
            randomTree(random, depth - 1), randomTree(random, depth - 1));
    }
    public static int run() {
        Solution s = new Solution();
        Checks.eq(3, s.pathSum(Checks.tree(10, 5, -3, 3, 2, null, 11, 3, -2, null, 1), 8));
        Checks.eq(0, s.pathSum(null, 0));
        Checks.eq(5, s.pathSum(Checks.tree(0, 0, 0), 0));
        Checks.eq(2, s.pathSum(Checks.tree(1, -1, -1), 0));
        // Prefix sum 2^32 must not wrap to zero.
        Checks.eq(0, s.pathSum(Checks.tree(1000000000, 1000000000, null, 1000000000,
            null, 1000000000, null, 294967296), 0));
        Random random = new Random(437);
        for (int i = 0; i < 300; i++) {
            TreeNode root = randomTree(random, 6);
            int target = random.nextInt(11) - 5;
            Checks.eq(brute(root, target), s.pathSum(root, target));
        }
        return Checks.count;
    }
}
