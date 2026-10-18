// Local checks for LeetCode 1851.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        Checks.eq(new int[]{3,3,1,4},s.minInterval(new int[][]{{1,4},{2,4},{3,6},{4,4}},new int[]{2,3,4,5}));
        Checks.eq(new int[]{2,-1,4,6},s.minInterval(new int[][]{{2,3},{2,5},{1,8},{20,25}},new int[]{2,19,5,22}));
        Checks.eq(new int[]{-1,1,3,1,1},s.minInterval(new int[][]{{1,1},{1,3},{2,2}},new int[]{4,2,3,2,1}));
        // The length-1 top expires first, followed later by a length-2 interval below it.
        Checks.eq(new int[]{1,10,-1},s.minInterval(new int[][]{{1,1},{1,2},{1,10}},new int[]{1,3,11}));
        int[][] original={{20,25},{1,8},{2,3}}; int[] orderedQueries={22,2,22,30};
        Checks.eq(new int[]{6,2,6,-1},s.minInterval(original,orderedQueries));
        Checks.eq(new int[][]{{20,25},{1,8},{2,3}},original);
        Checks.eq(new int[]{22,2,22,30},orderedQueries);
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
