// Local checks for LeetCode 155.
import java.util.*;
class Case {
    public static int run() {
        MinStack s = new MinStack(); s.push(-2); s.push(0); s.push(-3);
        Checks.eq(-3,s.getMin()); s.pop(); Checks.eq(0,s.top()); Checks.eq(-2,s.getMin());
        DifferenceMinStack d = new DifferenceMinStack(); d.push(Integer.MAX_VALUE); d.push(Integer.MIN_VALUE);
        Checks.eq(Integer.MIN_VALUE,d.getMin()); d.pop(); Checks.eq(Integer.MAX_VALUE,d.top());
        return Checks.count;
    }
}

