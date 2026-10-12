import java.util.*;
class Case {
    public static int run() {
        Solution s = new Solution(); DominanceSolution fast = new DominanceSolution();
        int[][] sample = {{0,0,0},{1,1,0},{0,0,0},{0,1,1},{0,0,0}};
        Checks.eq(6, s.shortestPath(sample, 1)); Checks.eq(6, fast.shortestPath(sample, 1));
        Checks.eq(-1, s.shortestPath(new int[][]{{0,1,1},{1,1,1},{1,0,0}}, 1));
        Checks.eq(0, s.shortestPath(new int[][]{{0}}, 0));
        for (int mask = 0; mask < 128; mask++) {
            int[][] grid = new int[3][3];
            for (int i = 1; i < 8; i++) { grid[i/3][i%3] = (mask >>> (i-1)) & 1; }
            for (int k = 0; k <= 5; k++) { Checks.eq(s.shortestPath(grid,k), fast.shortestPath(grid,k)); }
        }
        return Checks.count;
    }
}
