// Local checks for LeetCode 329.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        Checks.eq(4,s.longestIncreasingPath(new int[][]{{9,9,4},{6,6,8},{2,1,1}}));
        Checks.eq(4,s.longestIncreasingPath(new int[][]{{3,4,5},{3,2,6},{2,2,1}}));
        Checks.eq(1,s.longestIncreasingPath(new int[][]{{7,7},{7,7}}));
        int[][] line=new int[1][20000]; for (int i=0;i<line[0].length;i++) line[0][i]=i;
        Checks.eq(20000,s.longestIncreasingPath(line));
        return Checks.count;
    }
}

