// 138. Copy List with Random Pointer
// https://leetcode.com/problems/copy-list-with-random-pointer/
// Solution preserves input throughout; InterleavingSolution temporarily mutates and restores it.
import java.util.*;

class Solution {
    public Node copyRandomList(Node head) {
        Map<Node, Node> copy = new HashMap<>();
        for (Node p = head; p != null; p = p.next) {
            copy.put(p, new Node(p.val));
        }
        for (Node p = head; p != null; p = p.next) {
            copy.get(p).next = copy.get(p.next);
            copy.get(p).random = copy.get(p.random);
        }
        return copy.get(head);
    }
}

class InterleavingSolution {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        for (Node p = head; p != null; ) {
            Node next = p.next;
            p.next = new Node(p.val);
            p.next.next = next;
            p = next;
        }
        for (Node p = head; p != null; p = p.next.next) {
            p.next.random = p.random == null ? null : p.random.next;
        }
        Node result = head.next;
        for (Node p = head; p != null; ) {
            Node clone = p.next, next = clone.next;
            p.next = next;
            clone.next = next == null ? null : next.next;
            p = next;
        }
        return result;
    }
}
