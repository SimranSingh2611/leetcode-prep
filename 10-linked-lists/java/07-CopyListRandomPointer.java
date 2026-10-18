/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd first create one clone for each original node and remember the correspondence.
 * - A second pass wires next and random pointers through that map, handling cycles in random
 *   links without duplicating nodes.
 * Time complexity: O(n) expected for the map solution; O(n) for interleaving clones.
 * Space complexity: Primary: O(n) auxiliary map plus O(n) output clones; interleaving: O(1)
 *   auxiliary space excluding clones.
 */

// 138. Copy List with Random Pointer
// https://leetcode.com/problems/copy-list-with-random-pointer/
// NeetCode 150: https://neetcode.io/problems/copy-linked-list-with-random-pointer/question?list=neetcode150
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
