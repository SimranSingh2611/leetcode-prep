import java.util.*;
class Case {
    public static int run() {
        Solution s = new Solution();
        Checks.eq(3, s.numEnclaves(new int[][]{{0,0,0,0},{1,0,1,0},{0,1,1,0},{0,0,0,0}}));
        Checks.eq(0, s.numEnclaves(new int[][]{{0,1,1,0},{0,0,1,0},{0,0,1,0},{0,0,0,0}}));
        Checks.eq(0, s.numEnclaves(new int[][]{{1}}));
        return Checks.count;
    }
}
