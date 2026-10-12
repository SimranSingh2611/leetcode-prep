/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd add corresponding digits and carry while either list or the carry remains.
 * - Each step emits sum % 10 and carries sum / 10, avoiding whole-number conversion.
 * Time complexity: O(max(n, m)) for list lengths n and m.
 * Space complexity: O(1) auxiliary state, plus O(max(n, m)) output nodes.
 */

// 2. Add Two Numbers
// https://leetcode.com/problems/add-two-numbers/
// Solution preserves both input lists.
import java.util.*;

class Solution {
    public ListNode addTwoNumbers(ListNode a, ListNode b) {
        ListNode dummy = new ListNode(), tail = dummy;
        int carry = 0;
        while (a != null || b != null || carry != 0) {
            int sum = carry;
            if (a != null) {
                sum += a.val;
                a = a.next;
            }
            if (b != null) {
                sum += b.val;
                b = b.next;
            }
            tail.next = new ListNode(sum % 10);
            tail = tail.next;
            carry = sum / 10;
        }
        return dummy.next;
    }
}
