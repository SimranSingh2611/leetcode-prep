import java.util.*;
class Case {
    public static int run() {
        Solution s = new Solution();
        Checks.eq(2, s.shortestPathBinaryMatrix(new int[][]{{0,1},{1,0}}));
        Checks.eq(4, s.shortestPathBinaryMatrix(new int[][]{{0,0,0},{1,1,0},{1,1,0}}));
        Checks.eq(-1, s.shortestPathBinaryMatrix(new int[][]{{1}}));
        Checks.eq(1, s.shortestPathBinaryMatrix(new int[][]{{0}}));
        return Checks.count;
    }
}
