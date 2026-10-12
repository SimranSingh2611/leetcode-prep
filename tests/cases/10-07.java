// Local checks for LeetCode 138.
import java.util.*;
class Case {
    public static int run() {
        Node a=new Node(7),b=new Node(13);a.next=b;b.random=a;
        Node c=new Solution().copyRandomList(a);Checks.eq(false,c==a);Checks.eq(13,c.next.val);Checks.eq(true,c.next.random==c);
        Node d=new InterleavingSolution().copyRandomList(a);Checks.eq(true,a.next==b);Checks.eq(true,d.next.random==d);Checks.eq(false,d.next==b);
        return Checks.count;
    }
}

