// Local checks for LeetCode 57.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        Checks.eq(new int[][]{{1,5},{6,9}},s.insert(new int[][]{{1,3},{6,9}},new int[]{2,5}));
        Checks.eq(new int[][]{{1,2},{3,10},{12,16}},s.insert(new int[][]{{1,2},{3,5},{6,7},{8,10},{12,16}},new int[]{4,8}));
        Checks.eq(new int[][]{{2,3}},s.insert(new int[][]{},new int[]{2,3}));
        Checks.eq(new int[][]{{1,7}},s.insert(new int[][]{{1,2},{3,4},{6,7}},new int[]{2,6}));
        Checks.eq(new int[][]{{1,8}},s.insert(new int[][]{{1,8}},new int[]{2,3}));
        Checks.eq(new int[][]{{0,0},{2,3}},s.insert(new int[][]{{2,3}},new int[]{0,0}));
        int[][] original={{1,2},{5,7}}; int[] added={2,6};
        int[][] merged=s.insert(original,added);
        Checks.eq(new int[][]{{1,2},{5,7}},original);
        Checks.eq(new int[]{2,6},added);
        merged[0][1]=99;
        Checks.eq(new int[][]{{1,2},{5,7}},original);
        Checks.eq(new int[]{2,6},added);
        int[][] a={{1,2}}; int[] b={3,4}; int[][] out=s.insert(a,b); out[0][0]=99;
        Checks.eq(1,a[0][0]); Checks.eq(new int[]{3,4},b);
        return Checks.count;
    }
}
