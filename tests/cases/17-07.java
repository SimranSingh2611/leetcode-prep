// Local checks for LeetCode 421.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        Checks.eq(28,s.findMaximumXOR(new int[]{3,10,5,25,2,8}));
        Checks.eq(0,s.findMaximumXOR(new int[]{0}));
        Random rng=new Random(421);
        for (int t=0;t<100;t++) {
            int[] a=new int[1+rng.nextInt(20)]; for (int i=0;i<a.length;i++) a[i]=rng.nextInt(Integer.MAX_VALUE);
            int best=0; for (int x:a) for (int y:a) best=Math.max(best,x^y);
            Checks.eq(best,s.findMaximumXOR(a));
        }
        return Checks.count;
    }
}

