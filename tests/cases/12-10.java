// Local checks for LeetCode 297.
import java.util.*;
class Case {
    public static int run() {
        Codec c=new Codec();TreeNode t=Checks.tree(1,2,3,null,null,4,5);String encoded=c.serialize(t);
        Checks.eq(encoded,c.serialize(c.deserialize(encoded)));Checks.eq(null,c.deserialize(c.serialize(null)));
        TreeNode chain=new TreeNode(0),p=chain;for(int i=1;i<10000;i++){p.right=new TreeNode(i);p=p.right;}Checks.eq(c.serialize(chain),c.serialize(c.deserialize(c.serialize(chain))));
        return Checks.count;
    }
}

