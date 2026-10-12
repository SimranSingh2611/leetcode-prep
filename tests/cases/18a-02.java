import java.util.*;
class Case {
    public static int run() {
        Solution s = new Solution(); ConstantSpaceSolution fast = new ConstantSpaceSolution();
        Checks.eq(null, s.connect(null)); Checks.eq(null, fast.connect(null));
        for (int variant = 0; variant < 2; variant++) {
            Node root = new Node(1);
            root.left = new Node(2); root.right = new Node(3);
            root.left.left = new Node(4); root.left.right = new Node(5);
            root.right.left = new Node(6); root.right.right = new Node(7);
            Node result = variant == 0 ? s.connect(root) : fast.connect(root);
            Checks.eq(true, result == root); Checks.eq(true, root.left.next == root.right);
            Checks.eq(true, root.left.right.next == root.right.left);
            Checks.eq(null, root.right.right.next); Checks.eq(null, root.next);
        }
        return Checks.count;
    }
}
