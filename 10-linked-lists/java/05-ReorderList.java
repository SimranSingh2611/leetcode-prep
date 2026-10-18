/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd find the midpoint, split the list, and reverse the second half.
 * - Alternately weaving the two halves creates the requested order; splitting first prevents a
 *   cycle through the old middle link.
 * Time complexity: O(n) for n nodes.
 * Space complexity: O(1) auxiliary space; rewires input nodes.
 */

// 143. Reorder List
// https://leetcode.com/problems/reorder-list/
// NeetCode 150: https://neetcode.io/problems/reorder-linked-list/question?list=neetcode150
// Solution mutates links and returns void.
import java.util.*;

class Solution {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode current = slow.next;
        slow.next = null;
        ListNode reversed = null;
        while (current != null) {
            ListNode next = current.next;
            current.next = reversed;
            reversed = current;
            current = next;
        }
        ListNode first = head;
        while (reversed != null) {
            ListNode a = first.next, b = reversed.next;
            first.next = reversed;
            reversed.next = a;
            first = a;
            reversed = b;
        }
    }
}
