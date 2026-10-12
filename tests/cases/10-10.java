// Local checks for LeetCode 460.
import java.util.*;
class Case {
    public static int run() {
        LFUCache c=new LFUCache(2);c.put(1,1);c.put(2,2);Checks.eq(1,c.get(1));c.put(3,3);Checks.eq(-1,c.get(2));Checks.eq(3,c.get(3));
        c.put(4,4);Checks.eq(-1,c.get(1));Checks.eq(3,c.get(3));Checks.eq(4,c.get(4));
        return Checks.count;
    }
}

