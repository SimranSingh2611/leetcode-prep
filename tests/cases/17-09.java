// Local checks for LeetCode 472.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        Checks.eq(new HashSet<>(Arrays.asList("catsdogcats","dogcatsdog","ratcatdogcat")),
        new HashSet<>(s.findAllConcatenatedWordsInADict(new String[]{"cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"})));
        Checks.eq(Arrays.asList("catdog"),s.findAllConcatenatedWordsInADict(new String[]{"cat","dog","catdog"}));
        Checks.eq(Collections.emptyList(),s.findAllConcatenatedWordsInADict(new String[]{"cat"}));
        Checks.eq(Arrays.asList("aa","aaa"),s.findAllConcatenatedWordsInADict(new String[]{"a","aa","aaa"}));
        return Checks.count;
    }
}

