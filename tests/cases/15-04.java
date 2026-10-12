// Local checks for LeetCode 215.
import java.util.*;
class Case {
    public static int run() {
        Solution s = new Solution(); QuickselectSolution q = new QuickselectSolution();
        Checks.eq(5,s.findKthLargest(new int[]{3,2,1,5,6,4},2));
        Random rng = new Random(215);
        for (int t = 0; t < 100; t++) {
            int[] a = new int[1+rng.nextInt(40)];
            for (int i=0;i<a.length;i++) a[i]=rng.nextInt(15)-7;
            int[] sorted = a.clone(); Arrays.sort(sorted);
            for (int k=1;k<=a.length;k++) {
                Checks.eq(sorted[a.length-k],s.findKthLargest(a,k));
                Checks.eq(sorted[a.length-k],q.findKthLargest(a.clone(),k));
            }
        }
        return Checks.count;
    }
}

