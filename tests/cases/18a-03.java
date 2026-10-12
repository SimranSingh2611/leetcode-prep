// Local checks for LeetCode 133.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution(); Checks.eq(null,s.cloneGraph(null));
        Node a=new Node(1),b=new Node(2),c=new Node(3);
        a.neighbors.add(b); a.neighbors.add(c); b.neighbors.add(a); b.neighbors.add(c); c.neighbors.add(a); c.neighbors.add(b);
        Node copy=s.cloneGraph(a); Checks.eq(false,copy==a); Checks.eq(1,copy.val);
        Checks.eq(2,copy.neighbors.size()); Checks.eq(false,copy.neighbors.get(0)==b);
        Checks.eq(true,copy.neighbors.get(0).neighbors.get(0)==copy);
        Checks.eq(true,copy.neighbors.get(0).neighbors.get(1)==copy.neighbors.get(1));
        Node isolated=new Node(9); Checks.eq(0,s.cloneGraph(isolated).neighbors.size());
        return Checks.count;
    }
}
