import java.util.PriorityQueue;

/* LeetCode supplies:
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
*/

// Recommended: O(N log k) time and O(log k) recursive stack space.
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        return mergeRange(lists, 0, lists.length - 1);
    }

    private ListNode mergeRange(ListNode[] lists, int left, int right) {
        if (left > right) {
            return null;
        }
        if (left == right) {
            return lists[left];
        }
        int middle = left + (right - left) / 2;
        return mergeTwo(mergeRange(lists, left, middle), mergeRange(lists, middle + 1, right));
    }

    private ListNode mergeTwo(ListNode first, ListNode second) {
        ListNode dummy = new ListNode();
        ListNode tail = dummy;
        while (first != null && second != null) {
            if (first.val <= second.val) {
                tail.next = first;
                first = first.next;
            } else {
                tail.next = second;
                second = second.next;
            }
            tail = tail.next;
        }
        tail.next = first != null ? first : second;
        return dummy.next;
    }
}

// Follow-up: O(N log k) time and O(k) heap space; works naturally with streams.
class HeapSolution {
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> heap =
                new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));
        for (ListNode head : lists) {
            if (head != null) {
                heap.offer(head);
            }
        }

        ListNode dummy = new ListNode();
        ListNode tail = dummy;
        while (!heap.isEmpty()) {
            ListNode smallest = heap.poll();
            tail.next = smallest;
            tail = tail.next;
            if (smallest.next != null) {
                heap.offer(smallest.next);
            }
        }
        return dummy.next;
    }
}

