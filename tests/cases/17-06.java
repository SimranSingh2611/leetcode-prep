// Local checks for LeetCode 211.
import java.util.*;
class Case {
    public static int run() {
        WordDictionary s=new WordDictionary(); s.addWord("bad"); s.addWord("dad"); s.addWord("mad");
        Checks.eq(false,s.search("pad")); Checks.eq(true,s.search("bad")); Checks.eq(true,s.search(".ad"));
        Checks.eq(true,s.search("b..")); Checks.eq(false,s.search("..")); Checks.eq(false,s.search("...."));
        return Checks.count;
    }
}

