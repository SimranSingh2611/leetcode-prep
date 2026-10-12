/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd confirm that a complete group of k nodes exists before changing its links.
 * - Then I'd reverse that block, reconnect both ends, and leave any shorter final group
 *   untouched.
 * Time complexity: O(n) for n nodes.
 * Space complexity: O(1) auxiliary space.
 */

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
