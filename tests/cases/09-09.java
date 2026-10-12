// Local checks for LeetCode 901.
import java.util.*;
class Case {
    public static int run() {
        StockSpanner s=new StockSpanner(); int[] p={100,80,60,70,60,75,85}, expected={1,1,1,2,1,4,6};
        for(int i=0;i<p.length;i++) Checks.eq(expected[i],s.next(p[i]));
        StockSpanner equal = new StockSpanner();
        for (int i = 1; i <= 20; i++) Checks.eq(i, equal.next(100));
        return Checks.count;
    }
}
