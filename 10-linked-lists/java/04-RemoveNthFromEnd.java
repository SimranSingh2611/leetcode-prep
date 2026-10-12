/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd keep a fixed gap between fast and slow pointers starting from a dummy node.
 * - When fast reaches null, slow sits before the node to remove, including a removal of the
 *   head.
 * Time complexity: O(L) for list length L.
 * Space complexity: O(1) auxiliary space.
 */

// 19. Remove Nth Node From End of List
// https://leetcode.com/problems/remove-nth-node-from-end-of-list/
// Solution mutates one link; n is valid per the statement.
import java.util.*;

class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head), fast = dummy, slow = dummy;
        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return dummy.next;
    }
}
