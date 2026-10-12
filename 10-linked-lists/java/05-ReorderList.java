// 143. Reorder List
// https://leetcode.com/problems/reorder-list/
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
