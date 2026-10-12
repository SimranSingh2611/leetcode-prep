// Local checks for LeetCode 895.
import java.util.*;
class Case {
    public static int run() {
        FreqStack s = new FreqStack();
        for (int x : new int[]{5,7,5,7,4,5}) s.push(x);
        Checks.eq(5,s.pop()); Checks.eq(7,s.pop()); Checks.eq(5,s.pop()); Checks.eq(4,s.pop());
        return Checks.count;
    }
}

