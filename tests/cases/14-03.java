// Local checks for LeetCode 257.
import java.util.*;
class Case {
    public static int run() {
        Solution s = new Solution();
        Checks.eq(new HashSet<>(Arrays.asList("1->2->5","1->3")),
                  new HashSet<>(s.binaryTreePaths(Checks.tree(1,2,3,null,5))));
        Checks.eq(Arrays.asList("-1"), s.binaryTreePaths(Checks.tree(-1)));
        Checks.eq(0, s.binaryTreePaths(null).size());
        return Checks.count;
    }
}

