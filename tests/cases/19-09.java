// Local checks for LeetCode 2392.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution(); int[][] rows={{1,2},{3,2}},cols={{2,1},{3,2}};
        int[][] b=s.buildMatrix(3,rows,cols); Checks.eq(3,b.length);
        int[] r=new int[4],c=new int[4],count=new int[4];
        for (int i=0;i<3;i++) for (int j=0;j<3;j++) if (b[i][j]!=0) { int v=b[i][j]; r[v]=i;c[v]=j;count[v]++; }
        for (int v=1;v<=3;v++) Checks.eq(1,count[v]);
        for (int[] e:rows) Checks.eq(true,r[e[0]]<r[e[1]]);
        for (int[] e:cols) Checks.eq(true,c[e[0]]<c[e[1]]);
        Checks.eq(0,s.buildMatrix(3,new int[][]{{1,2},{2,1}},new int[][]{}).length);
        Checks.eq(new int[][]{{1}},s.buildMatrix(1,new int[][]{},new int[][]{}));
        return Checks.count;
    }
}

