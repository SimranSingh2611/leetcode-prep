// Local checks for LeetCode 463.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution(); ScanSolution scan=new ScanSolution();
        int[][] a={{0,1,0,0},{1,1,1,0},{0,1,0,0},{1,1,0,0}};
        Checks.eq(16,s.islandPerimeter(a)); Checks.eq(16,scan.islandPerimeter(a));
        Checks.eq(4,s.islandPerimeter(new int[][]{{1}})); Checks.eq(6,scan.islandPerimeter(new int[][]{{1,1}}));
        Checks.eq(16, new BfsSolution().islandPerimeter(a));
        return Checks.count;
    }
}
