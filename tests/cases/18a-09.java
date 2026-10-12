import java.util.*;
class Case {
    public static int run() {
        Solution s = new Solution();
        Checks.eq(new int[][]{{0,0,0},{0,1,0},{1,2,1}}, s.updateMatrix(new int[][]{{0,0,0},{0,1,0},{1,1,1}}));
        Random rng = new Random(542);
        for (int t = 0; t < 100; t++) {
            int[][] a = new int[5][6];
            for (int[] row : a) { for (int c = 0; c < row.length; c++) { row[c] = rng.nextInt(2); } }
            a[0][0] = 0; int[][] actual = s.updateMatrix(a);
            for (int r = 0; r < 5; r++) { for (int c = 0; c < 6; c++) {
                int best = 100;
                for (int rr = 0; rr < 5; rr++) { for (int cc = 0; cc < 6; cc++) {
                    if (a[rr][cc] == 0) { best = Math.min(best, Math.abs(r-rr)+Math.abs(c-cc)); }
                } }
                Checks.eq(best, actual[r][c]);
            } }
        }
        return Checks.count;
    }
}
