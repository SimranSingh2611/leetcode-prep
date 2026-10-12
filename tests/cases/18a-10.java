import java.util.*;
class Case {
    public static int run() {
        Solution s = new Solution();
        Checks.eq(2, s.maxDistance(new int[][]{{1,0,1},{0,0,0},{1,0,1}}));
        Checks.eq(4, s.maxDistance(new int[][]{{1,0,0},{0,0,0},{0,0,0}}));
        Checks.eq(-1, s.maxDistance(new int[][]{{1,1},{1,1}}));
        Checks.eq(-1, s.maxDistance(new int[][]{{0}}));
        return Checks.count;
    }
}
