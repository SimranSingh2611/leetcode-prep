// Local checks for LeetCode 778.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        Checks.eq(3,s.swimInWater(new int[][]{{0,2},{1,3}}));
        Checks.eq(16,s.swimInWater(new int[][]{{0,1,2,3,4},{24,23,22,21,5},{12,13,14,15,16},{11,17,18,19,20},{10,9,8,7,6}}));
        Checks.eq(0,s.swimInWater(new int[][]{{0}}));
        Random rng=new Random(778);
        for (int t=0;t<50;t++) {
            int n=4; List<Integer> heights=new ArrayList<>(); for (int i=0;i<n*n;i++) heights.add(i);
            Collections.shuffle(heights,rng); int[][] b=new int[n][n];
            for (int i=0;i<n*n;i++) b[i/n][i%n]=heights.get(i);
            int expected=-1;
            for (int level=0;level<n*n;level++) {
                boolean[] seen=new boolean[n*n]; ArrayDeque<Integer> q=new ArrayDeque<>();
                if (b[0][0]<=level) { q.offer(0); seen[0]=true; }
                while (!q.isEmpty()) {
                    int id=q.poll(),r=id/n,c=id%n;
                    for (int next:new int[]{id-n,id+n,id-1,id+1}) {
                        if (next<0 || next>=n*n || Math.abs(next/n-r)+Math.abs(next%n-c)!=1 || seen[next] || b[next/n][next%n]>level) continue;
                        seen[next]=true; q.offer(next);
                    }
                }
                if (seen[n*n-1]) { expected=level; break; }
            }
            Checks.eq(expected,s.swimInWater(b));
        }
        return Checks.count;
    }
}

