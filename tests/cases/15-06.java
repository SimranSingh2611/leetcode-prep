// Local checks for LeetCode 621.
import java.util.*;
class Case {
    public static int run() {
        Solution s = new Solution(); FormulaSolution f = new FormulaSolution();
        Checks.eq(8,s.leastInterval("AAABBB".toCharArray(),2));
        Checks.eq(6,s.leastInterval("AAABBB".toCharArray(),0));
        Random rng = new Random(621);
        for (int t=0;t<100;t++) {
            char[] a=new char[1+rng.nextInt(100)];
            for (int i=0;i<a.length;i++) a[i]=(char)('A'+rng.nextInt(8));
            int n=rng.nextInt(10);
            Checks.eq(f.leastInterval(a,n),s.leastInterval(a,n));
        }
        return Checks.count;
    }
}

