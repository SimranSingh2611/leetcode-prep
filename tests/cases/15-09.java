// Local checks for LeetCode 295.
import java.util.*;
class Case {
    public static int run() {
        MedianFinder s=new MedianFinder(); s.addNum(1); s.addNum(2); Checks.close(1.5,s.findMedian());
        s.addNum(3); Checks.close(2.0,s.findMedian());
        MedianFinder big=new MedianFinder(); big.addNum(Integer.MAX_VALUE); big.addNum(Integer.MAX_VALUE);
        Checks.close(Integer.MAX_VALUE,big.findMedian());
        MedianFinder random=new MedianFinder(); List<Integer> a=new ArrayList<>(); Random rng=new Random(295);
        for (int i=0;i<200;i++) {
            int x=rng.nextInt(2001)-1000; random.addNum(x); a.add(x); Collections.sort(a);
            int n=a.size(); double expected=n%2==1 ? a.get(n/2) : (a.get(n/2-1)+a.get(n/2))/2.0;
            Checks.close(expected,random.findMedian());
        }
        return Checks.count;
    }
}

