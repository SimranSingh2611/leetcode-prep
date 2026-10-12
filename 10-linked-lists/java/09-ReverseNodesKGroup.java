// 25. Reverse Nodes in k-Group
// https://leetcode.com/problems/reverse-nodes-in-k-group/
// Solution mutates links, not node values.
import java.util.*;

class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0, head), before = dummy;
        while (true) {
            ListNode kth = before;
            for (int i = 0; i < k && kth != null; i++) {
                kth = kth.next;
            }
            if (kth == null) {
                break;
            }
            ListNode after = kth.next, current = before.next, previous = after, oldHead = current;
            while (current != after) {
                ListNode next = current.next;
                current.next = previous;
                previous = current;
                current = next;
            }
            before.next = kth;
            before = oldHead;
        }
        return dummy.next;
    }
}
