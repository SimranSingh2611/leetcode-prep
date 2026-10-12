// Local checks for LeetCode 1863.
import java.util.*;
class Case {
    public static int run() {
        Solution s = new Solution(); BitContributionSolution fast = new BitContributionSolution();
        Checks.eq(6, s.subsetXORSum(new int[]{1,3}));
        Checks.eq(28, fast.subsetXORSum(new int[]{5,1,6}));
        Random rng = new Random(1863);
        for (int t = 0; t < 100; t++) {
            int[] a = new int[1 + rng.nextInt(10)];
            for (int i = 0; i < a.length; i++) a[i] = rng.nextInt(21);
            Checks.eq(s.subsetXORSum(a), fast.subsetXORSum(a));
        }
        return Checks.count;
    }
}

