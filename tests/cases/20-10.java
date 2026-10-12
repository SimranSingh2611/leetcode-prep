// Local checks for LeetCode 1579.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        Checks.eq(2,s.maxNumEdgesToRemove(4,new int[][]{{3,1,2},{3,2,3},{1,1,3},{1,2,4},{1,1,2},{2,3,4}}));
        Checks.eq(0,s.maxNumEdgesToRemove(4,new int[][]{{3,1,2},{3,2,3},{1,1,4},{2,1,4}}));
        Checks.eq(-1,s.maxNumEdgesToRemove(4,new int[][]{{3,2,3},{1,1,2},{2,3,4}}));
        Random rng=new Random(1579);
        for (int t=0;t<40;t++) {
            int n=4; List<int[]> possible=new ArrayList<>();
            for (int type=1;type<=3;type++) for (int u=1;u<=n;u++) for (int v=u+1;v<=n;v++) possible.add(new int[]{type,u,v});
            Collections.shuffle(possible,rng); int m=1+rng.nextInt(9);
            int[][] edges=possible.subList(0,m).toArray(new int[0][]); int expected=-1;
            for (int mask=0;mask<(1<<m);mask++) {
                boolean valid=true;
                for (int person=1;person<=2;person++) {
                    boolean[] seen=new boolean[n+1]; seen[1]=true;
                    for (int pass=0;pass<n;pass++) for (int i=0;i<m;i++)
                        if ((mask&(1<<i))!=0 && (edges[i][0]==3 || edges[i][0]==person)) {
                            int u=edges[i][1],v=edges[i][2]; if (seen[u] || seen[v]) seen[u]=seen[v]=true;
                        }
                    for (int v=1;v<=n;v++) valid&=seen[v];
                }
                if (valid) expected=Math.max(expected,m-Integer.bitCount(mask));
            }
            Checks.eq(expected,s.maxNumEdgesToRemove(n,edges));
        }
        return Checks.count;
    }
}

