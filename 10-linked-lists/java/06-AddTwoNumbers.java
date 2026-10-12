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
