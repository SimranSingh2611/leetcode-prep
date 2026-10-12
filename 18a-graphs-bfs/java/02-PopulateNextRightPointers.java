// 116. Populating Next Right Pointers in Each Node
// https://leetcode.com/problems/populating-next-right-pointers-in-each-node/
// Both mutate next pointers, not left/right child links. Judge Node has val, left, right, next.
import java.util.*;

class Solution {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            Node previous = null;
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                if (previous != null) {
                    previous.next = node;
                }
                previous = node;
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            previous.next = null;
        }
        return root;
    }
}

class ConstantSpaceSolution {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }
        root.next = null;
        Node level = root;
        while (level.left != null) {
            for (Node parent = level; parent != null; parent = parent.next) {
                parent.left.next = parent.right;
                parent.right.next = parent.next == null ? null : parent.next.left;
            }
            level = level.left;
        }
        return root;
    }
}
