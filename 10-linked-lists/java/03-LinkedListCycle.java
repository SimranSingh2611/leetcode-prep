/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd move one pointer by one link and another by two.
 * - Inside a cycle their relative positions eventually meet; reaching null proves there is no
 *   cycle.
 * Time complexity: O(n) for n reachable nodes.
 * Space complexity: Primary Floyd solution: O(1) auxiliary space; the visited-set alternative
 *   uses O(n).
 */

// 141. Linked List Cycle
// https://leetcode.com/problems/linked-list-cycle/
// Solution is the optimized Floyd method; no mutation.
import java.util.*;

class HashSetSolution {
    public boolean hasCycle(ListNode head) {
        Set<ListNode> seen = new HashSet<>();
        while (head != null) {
            if (!seen.add(head)) {
                return true;
            }
            head = head.next;
        }
        return false;
    }
}

class Solution {
    public boolean hasCycle(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}
