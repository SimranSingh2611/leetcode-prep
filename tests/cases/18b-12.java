import java.util.*;
class Case {
    public static int run() {
        Solution s = new Solution();
        Checks.eq(new HashSet<>(Arrays.asList(Arrays.asList(0,1,3),Arrays.asList(0,2,3))),
        new HashSet<>(s.allPathsSourceTarget(new int[][]{{1,2},{3},{3},{}})));
        Checks.eq(5, s.allPathsSourceTarget(new int[][]{{4,3,1},{3,2,4},{3},{4},{}}).size());
        Checks.eq(Collections.emptyList(), s.allPathsSourceTarget(new int[][]{{},{}}));
        return Checks.count;
    }
}
