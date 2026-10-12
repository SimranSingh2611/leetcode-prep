// Local checks for LeetCode 1462.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        Checks.eq(Arrays.asList(false,true),s.checkIfPrerequisite(2,new int[][]{{1,0}},new int[][]{{0,1},{1,0}}));
        Checks.eq(Arrays.asList(true,true,false),s.checkIfPrerequisite(4,new int[][]{{0,1},{1,2},{2,3}},new int[][]{{0,3},{1,3},{3,0}}));
        Checks.eq(Arrays.asList(false),s.checkIfPrerequisite(2,new int[][]{},new int[][]{{0,1}}));
        Random rng=new Random(1462);
        for (int t=0;t<50;t++) {
            int n=8; List<int[]> edges=new ArrayList<>(); boolean[][] reach=new boolean[n][n];
            for (int u=0;u<n;u++) for (int v=u+1;v<n;v++) if (rng.nextBoolean()) { edges.add(new int[]{u,v}); reach[u][v]=true; }
            for (int k=0;k<n;k++) for (int i=0;i<n;i++) for (int j=0;j<n;j++) reach[i][j]|=reach[i][k] && reach[k][j];
            List<int[]> queries=new ArrayList<>(); List<Boolean> expected=new ArrayList<>();
            for (int i=0;i<n;i++) for (int j=0;j<n;j++) if (i!=j) { queries.add(new int[]{i,j}); expected.add(reach[i][j]); }
            Checks.eq(expected,s.checkIfPrerequisite(n,edges.toArray(new int[0][]),queries.toArray(new int[0][])));
        }
        return Checks.count;
    }
}

