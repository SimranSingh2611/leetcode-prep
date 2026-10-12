import java.util.*;
class Case {
    public static int run() {
        Solution s = new Solution();
        Checks.eq(true, s.validTree(5, new int[][]{{0,1},{0,2},{0,3},{1,4}}));
        Checks.eq(false, s.validTree(5, new int[][]{{0,1},{1,2},{2,3},{1,3},{1,4}}));
        Checks.eq(false, s.validTree(4, new int[][]{{0,1},{1,2},{2,0}}));
        Checks.eq(true, s.validTree(1, new int[][]{}));
        return Checks.count;
    }
}
