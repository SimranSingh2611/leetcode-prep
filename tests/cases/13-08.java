// Local checks for LeetCode 173.
import java.util.*;
class Case {
    public static int run() {
        BSTIterator it=new BSTIterator(Checks.tree(7,3,15,null,null,9,20));for(int x:new int[]{3,7,9,15,20}){Checks.eq(true,it.hasNext());Checks.eq(x,it.next());}Checks.eq(false,it.hasNext());
        return Checks.count;
    }
}

