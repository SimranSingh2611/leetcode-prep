// Local checks for LeetCode 210.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution(); int[][] edges={{1,0},{2,0},{3,1},{3,2}};
        int[] order=s.findOrder(4,edges); Checks.eq(4,order.length);
        int[] rank=new int[4]; boolean[] seen=new boolean[4];
        for (int i=0;i<order.length;i++) { Checks.eq(false,seen[order[i]]); seen[order[i]]=true; rank[order[i]]=i; }
        for (int[] e:edges) Checks.eq(true,rank[e[1]]<rank[e[0]]);
        Checks.eq(0,s.findOrder(2,new int[][]{{1,0},{0,1}}).length);
        Checks.eq(3,s.findOrder(3,new int[][]{}).length);
        return Checks.count;
    }
}

