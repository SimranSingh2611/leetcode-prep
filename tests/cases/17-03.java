// Local checks for LeetCode 677.
import java.util.*;
class Case {
    public static int run() {
        MapSum s=new MapSum(); s.insert("apple",3); Checks.eq(3,s.sum("ap"));
        s.insert("app",2); Checks.eq(5,s.sum("ap")); s.insert("apple",2); Checks.eq(4,s.sum("ap"));
        Checks.eq(0,s.sum("b")); s.insert("app",2); Checks.eq(4,s.sum("ap"));
        return Checks.count;
    }
}

