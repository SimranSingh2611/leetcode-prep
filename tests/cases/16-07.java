// Local checks for LeetCode 763.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        Checks.eq(Arrays.asList(9,7,8),s.partitionLabels("ababcbacadefegdehijhklij"));
        Checks.eq(Arrays.asList(10),s.partitionLabels("eccbbbbdec"));
        Checks.eq(Arrays.asList(1,1,1),s.partitionLabels("abc"));
        return Checks.count;
    }
}

