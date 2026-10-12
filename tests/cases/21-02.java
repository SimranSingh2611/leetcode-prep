// Local checks for LeetCode 56: compare with connected components of overlapping ranges.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        Checks.eq(new int[][]{{1,6},{8,10},{15,18}},s.merge(new int[][]{{1,3},{2,6},{8,10},{15,18}}));
        Checks.eq(new int[][]{{1,5}},s.merge(new int[][]{{1,4},{4,5}}));
        Checks.eq(new int[][]{{1,10}},s.merge(new int[][]{{2,3},{1,10},{4,7},{4,7}}));
        Checks.eq(new int[][]{{2,2}},s.merge(new int[][]{{2,2},{2,2}}));
        Checks.eq(new int[0][],s.merge(new int[0][]));
        Checks.eq(new int[][]{{Integer.MIN_VALUE,-1},{0,Integer.MAX_VALUE}},
                  s.merge(new int[][]{{0,Integer.MAX_VALUE},{Integer.MIN_VALUE,-1}}));
        int[][] original={{5,7},{1,3},{2,4}};
        int[][] out=s.merge(original);
        Checks.eq(new int[][]{{5,7},{1,3},{2,4}},original);
        out[0][0]=99; out[1][1]=99;
        Checks.eq(new int[][]{{5,7},{1,3},{2,4}},original);
        Random rng=new Random(56);
        for(int t=0;t<150;t++) {
            int[][] a=new int[rng.nextInt(9)][2];
            for(int[] row:a) {row[0]=rng.nextInt(15)-5;row[1]=row[0]+rng.nextInt(6);}
            Checks.eq(brute(a),s.merge(a));
        }
        return Checks.count;
    }
    static int[][] brute(int[][] a) {
        boolean[] seen=new boolean[a.length];
        List<int[]> ranges=new ArrayList<>();
        for(int i=0;i<a.length;i++) if(!seen[i]) {
            int lo=a[i][0],hi=a[i][1];
            Deque<Integer> pending=new ArrayDeque<>();pending.add(i);seen[i]=true;
            while(!pending.isEmpty()) {
                int current=pending.remove();
                lo=Math.min(lo,a[current][0]);hi=Math.max(hi,a[current][1]);
                for(int j=0;j<a.length;j++)
                    if(!seen[j] && a[current][0]<=a[j][1] && a[j][0]<=a[current][1]) {
                        seen[j]=true;pending.add(j);
                    }
            }
            ranges.add(new int[]{lo,hi});
        }
        ranges.sort((a1,b)->Integer.compare(a1[0],b[0]));
        return ranges.toArray(new int[ranges.size()][]);
    }
}
