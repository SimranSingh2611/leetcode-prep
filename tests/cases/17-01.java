// Local checks for LeetCode 208.
import java.util.*;
class Case {
    public static int run() {
        Trie s=new Trie(); s.insert("apple");
        Checks.eq(true,s.search("apple")); Checks.eq(false,s.search("app"));
        Checks.eq(true,s.startsWith("app")); s.insert("app"); Checks.eq(true,s.search("app"));
        Checks.eq(false,s.startsWith("b")); s.insert("apple"); Checks.eq(true,s.search("apple"));
        return Checks.count;
    }
}

