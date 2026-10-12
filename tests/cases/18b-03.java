import java.util.*;
class Case {
    public static int run() {
        Solution s = new Solution();
        Checks.eq(2, s.countComponents(5, new int[][]{{0,1},{1,2},{3,4}}));
        Checks.eq(1, s.countComponents(5, new int[][]{{0,1},{1,2},{2,3},{3,4}}));
        Checks.eq(4, s.countComponents(4, new int[][]{}));
        Checks.eq(2, s.countComponents(4, new int[][]{{0,1},{1,2},{2,0}}));
        return Checks.count;
    }
}
