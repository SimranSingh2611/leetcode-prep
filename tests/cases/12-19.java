// Local checks for LeetCode 114: preserve preorder identities and reuse all nodes.
import java.util.*;
class Case {
    private static void check(TreeNode root) {
        List<TreeNode> preorder = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        if (root != null) stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            preorder.add(node);
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }
        Solution s = new Solution();
        s.flatten(root);
        for (int repeat = 0; repeat < 2; repeat++) {
            TreeNode node = root;
            for (TreeNode original : preorder) {
                Checks.eq(true, node == original);
                Checks.eq(null, node.left);
                node = node.right;
            }
            Checks.eq(null, node);
            s.flatten(root);
        }
    }
    public static int run() {
        check(Checks.tree(1, 2, 5, 3, 4, null, 6));
        check(Checks.tree(1, 2, 3, null, 4, 5, null, 6));
        check(Checks.tree(1, 1, 1, 1, 1));
        check(null);
        check(new TreeNode(0));
        TreeNode chain = null;
        for (int i = 0; i < 2000; i++) chain = new TreeNode(i, chain, null);
        check(chain);
        return Checks.count;
    }
}
