// Local checks for LeetCode 1851.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        Checks.eq(new int[]{3,3,1,4},s.minInterval(new int[][]{{1,4},{2,4},{3,6},{4,4}},new int[]{2,3,4,5}));
        Checks.eq(new int[]{2,-1,4,6},s.minInterval(new int[][]{{2,3},{2,5},{1,8},{20,25}},new int[]{2,19,5,22}));
        Random rng=new Random(1851);
        for (int t=0;t<100;t++) {
            int[][] a=new int[1+rng.nextInt(12)][2];
            for (int[] row:a) { row[0]=rng.nextInt(20); row[1]=row[0]+rng.nextInt(10); }
            int[] queries=new int[20],expected=new int[20];
            for (int i=0;i<20;i++) {
                queries[i]=rng.nextInt(35); int best=Integer.MAX_VALUE;
                for (int[] row:a) if (row[0]<=queries[i] && queries[i]<=row[1]) best=Math.min(best,row[1]-row[0]+1);
                expected[i]=best==Integer.MAX_VALUE ? -1 : best;
            }
            Checks.eq(expected,s.minInterval(a,queries));
        }
        return Checks.count;
    }
}

