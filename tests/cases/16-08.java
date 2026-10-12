// Local checks for LeetCode 57.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        Checks.eq(new int[][]{{1,5},{6,9}},s.insert(new int[][]{{1,3},{6,9}},new int[]{2,5}));
        Checks.eq(new int[][]{{1,2},{3,10},{12,16}},s.insert(new int[][]{{1,2},{3,5},{6,7},{8,10},{12,16}},new int[]{4,8}));
        Checks.eq(new int[][]{{2,3}},s.insert(new int[][]{},new int[]{2,3}));
        int[][] a={{1,2}}; int[] b={3,4}; int[][] out=s.insert(a,b); out[0][0]=99;
        Checks.eq(1,a[0][0]); Checks.eq(new int[]{3,4},b);
        return Checks.count;
    }
}

