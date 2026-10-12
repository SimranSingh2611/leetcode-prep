/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd use a dummy head and append the smaller of the two current nodes.
 * - Once one list ends, I'd link the remaining suffix directly, reusing the existing nodes.
 * Time complexity: O(n + m) for the two list lengths.
 * Space complexity: O(1) auxiliary space; reuses input nodes.
 */

// 21. Merge Two Sorted Lists
// https://leetcode.com/problems/merge-two-sorted-lists/
// Solution reuses and relinks input nodes.
import java.util.*;

class Solution {
    public ListNode mergeTwoLists(ListNode a, ListNode b) {
        ListNode dummy = new ListNode(), tail = dummy;
        while (a != null && b != null) {
            if (a.val <= b.val) {
                tail.next = a;
                a = a.next;
            } else {
                tail.next = b;
                b = b.next;
            }
            tail = tail.next;
        }
        tail.next = a != null ? a : b;
        return dummy.next;
    }
}
