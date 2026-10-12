import java.util.*;
class Case {
    public static int run() {
        Solution s = new Solution();
        Checks.eq(Collections.emptyList(), s.levelOrder(null));
        Node root = new Node(1), three = new Node(3);
        root.children.add(three); root.children.add(new Node(2)); root.children.add(new Node(4));
        three.children.add(new Node(5)); three.children.add(new Node(6));
        Checks.eq(Arrays.asList(Arrays.asList(1), Arrays.asList(3,2,4), Arrays.asList(5,6)), s.levelOrder(root));
        return Checks.count;
    }
}
