// Local checks for LeetCode 143.
import java.util.*;
class Case {
    public static int run() {
        ListNode a=Checks.list(1,2,3,4,5);new Solution().reorderList(a);Checks.eq(new int[]{1,5,2,4,3},Checks.values(a));
        ListNode b=Checks.list(1,2,3,4);new Solution().reorderList(b);Checks.eq(new int[]{1,4,2,3},Checks.values(b));
        return Checks.count;
    }
}

