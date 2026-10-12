// Local checks for LeetCode 127.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        Checks.eq(5,s.ladderLength("hit","cog",Arrays.asList("hot","dot","dog","lot","log","cog")));
        Checks.eq(0,s.ladderLength("hit","cog",Arrays.asList("hot","dot","dog","lot","log")));
        Checks.eq(2,s.ladderLength("a","c",Arrays.asList("a","b","c")));
        Checks.eq(0,s.ladderLength("aaa","bbb",Arrays.asList("bbb")));
        return Checks.count;
    }
}
