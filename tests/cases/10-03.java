// Local checks for LeetCode 141.
import java.util.*;
class Case {
    public static int run() {
        ListNode h=Checks.list(3,2,0,-4);h.next.next.next.next=h.next;
        Checks.eq(true,new Solution().hasCycle(h));Checks.eq(true,new HashSetSolution().hasCycle(h));
        Checks.eq(false,new Solution().hasCycle(Checks.list(1,1)));
        return Checks.count;
    }
}

