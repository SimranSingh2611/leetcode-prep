import java.util.*;
class Case {
    public static int run() {
        Solution s = new Solution();
        Checks.eq(3, s.countSubIslands(new int[][]{{1,1,1,0,0},{0,1,1,1,1},{0,0,0,0,0},{1,0,0,0,0},{1,1,0,1,1}},
        new int[][]{{1,1,1,0,0},{0,0,1,1,1},{0,1,0,0,0},{1,0,1,1,0},{0,1,0,1,0}}));
        int[][] second = {{1,1,1},{1,1,1}};
        Checks.eq(0, s.countSubIslands(new int[][]{{0,1,1},{1,1,1}},second));
        Checks.eq(new int[][]{{0,0,0},{0,0,0}},second);
        Checks.eq(1, s.countSubIslands(new int[][]{{1}},new int[][]{{1}}));
        return Checks.count;
    }
}
