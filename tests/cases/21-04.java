// Local checks for LeetCode 435: enumerate all retained subsets on small inputs.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        Checks.eq(1,s.eraseOverlapIntervals(new int[][]{{1,2},{2,3},{3,4},{1,3}}));
        Checks.eq(2,s.eraseOverlapIntervals(new int[][]{{1,2},{1,2},{1,2}}));
        Checks.eq(0,s.eraseOverlapIntervals(new int[][]{{1,2},{2,3}}));
        Checks.eq(1,s.eraseOverlapIntervals(new int[][]{{1,10},{2,3},{3,4},{4,5}}));
        Checks.eq(0,s.eraseOverlapIntervals(new int[0][]));
        Checks.eq(0,s.eraseOverlapIntervals(new int[][]{{0,Integer.MAX_VALUE},{Integer.MIN_VALUE,0}}));
        int[][] original={{5,8},{-3,2},{0,7}};
        Checks.eq(1,s.eraseOverlapIntervals(original));
        Checks.eq(new int[][]{{5,8},{-3,2},{0,7}},original);
        Random rng=new Random(435);
        for(int t=0;t<150;t++) {
            int[][] a=new int[rng.nextInt(9)][2];
            for(int[] row:a) {row[0]=rng.nextInt(15)-5;row[1]=row[0]+1+rng.nextInt(6);}
            int mostKept=0;
            for(int mask=0;mask<(1<<a.length);mask++) {
                boolean valid=true;
                for(int i=0;i<a.length;i++) if((mask&(1<<i))!=0)
                    for(int j=i+1;j<a.length;j++) if((mask&(1<<j))!=0)
                        if(a[i][0]<a[j][1] && a[j][0]<a[i][1]) valid=false;
                if(valid) mostKept=Math.max(mostKept,Integer.bitCount(mask));
            }
            Checks.eq(a.length-mostKept,s.eraseOverlapIntervals(a));
        }
        return Checks.count;
    }
}
