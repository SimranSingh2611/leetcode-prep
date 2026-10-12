// Local checks for LeetCode 417.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        Checks.eq(new HashSet<>(Arrays.asList(Arrays.asList(0,4),Arrays.asList(1,3),Arrays.asList(1,4),Arrays.asList(2,2),Arrays.asList(3,0),Arrays.asList(3,1),Arrays.asList(4,0))),
        new HashSet<>(s.pacificAtlantic(new int[][]{{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}})));
        Checks.eq(Arrays.asList(Arrays.asList(0,0)),s.pacificAtlantic(new int[][]{{7}}));
        Checks.eq(4,s.pacificAtlantic(new int[][]{{2,2},{2,2}}).size());
        Random rng = new Random(417);
        for (int t = 0; t < 100; t++) {
            int[][] a = new int[5][5];
            for (int[] row : a) { for (int i = 0; i < row.length; i++) { row[i] = rng.nextInt(8); } }
            Checks.eq(new HashSet<>(new BfsSolution().pacificAtlantic(a)), new HashSet<>(s.pacificAtlantic(a)));
        }
        return Checks.count;
    }
}
