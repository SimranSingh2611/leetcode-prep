// Local checks for LeetCode 703.
import java.util.*;
class Case {
    public static int run() {
        KthLargest s = new KthLargest(3,new int[]{4,5,8,2});
        Checks.eq(4,s.add(3)); Checks.eq(5,s.add(5)); Checks.eq(5,s.add(10));
        Checks.eq(8,s.add(9)); Checks.eq(8,s.add(4));
        KthLargest one = new KthLargest(1,new int[]{});
        Checks.eq(-3,one.add(-3)); Checks.eq(-3,one.add(-4)); Checks.eq(7,one.add(7));
        return Checks.count;
    }
}

