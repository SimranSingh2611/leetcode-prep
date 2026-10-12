/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd save the next node before reversing each current link toward the previous node.
 * - That keeps the unprocessed suffix reachable while building the reversed prefix.
 * Time complexity: O(n) for n nodes, for both versions.
 * Space complexity: Primary iterative: O(1) auxiliary space; recursive alternative: O(n) stack.
 */

// 206. Reverse Linked List
// https://leetcode.com/problems/reverse-linked-list/
// Solution iterative; RecursiveSolution alternative. Both mutate links.
import java.util.*;

class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode previous = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = previous;
            previous = head;
            head = next;
        }
        return previous;
    }
}

class RecursiveSolution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode result = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return result;
    }
}
