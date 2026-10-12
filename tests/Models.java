import java.util.*;

// Judge-provided types, included only for isolated local compilation.
class ListNode {
    int val; ListNode next;
    ListNode() {}
    ListNode(int value) { val=value; }
    ListNode(int value,ListNode following) { val=value; next=following; }
}
class TreeNode {
    int val; TreeNode left,right;
    TreeNode() {}
    TreeNode(int value) { val=value; }
    TreeNode(int value,TreeNode l,TreeNode r) { val=value; left=l; right=r; }
}
class Node {
    int val; Node next,random,left,right;
    List<Node> neighbors;
    List<Node> children = new ArrayList<>();
    Node() { neighbors=new ArrayList<>(); }
    Node(int value) { val=value; neighbors=new ArrayList<>(); }
    Node(int value,ArrayList<Node> links) { val=value; neighbors=links; }
}
// Minimal test-only contract for problem 339. LeetCode supplies its own interface.
interface NestedInteger {
    boolean isInteger();
    Integer getInteger();
    List<NestedInteger> getList();
}
class TestNested implements NestedInteger {
    private final Integer value;
    private final List<NestedInteger> list;
    TestNested(int value) { this.value=value; this.list=null; }
    TestNested(List<NestedInteger> list) { this.value=null; this.list=list; }
    public boolean isInteger() { return value!=null; }
    public Integer getInteger() { return value; }
    public List<NestedInteger> getList() { return list; }
}
class Checks {
    static int count;
    static void eq(Object expected,Object actual) {
        count++;
        if(!Objects.deepEquals(expected,actual))
            throw new AssertionError("Expected "+format(expected)+", got "+format(actual));
    }
    static void close(double expected,double actual) {
        count++;
        if(Double.compare(expected,actual)==0)return;
        if(!Double.isFinite(expected) || !Double.isFinite(actual)
                || Math.abs(expected-actual)>1e-8*Math.max(1,Math.abs(expected)))
            throw new AssertionError("Expected "+expected+", got "+actual);
    }
    static String format(Object x) {
        if(x instanceof int[])return Arrays.toString((int[])x);
        if(x instanceof long[])return Arrays.toString((long[])x);
        if(x instanceof Object[])return Arrays.deepToString((Object[])x);
        return String.valueOf(x);
    }
    static ListNode list(int... values) {
        ListNode dummy=new ListNode(),tail=dummy;
        for(int value:values){tail.next=new ListNode(value);tail=tail.next;}
        return dummy.next;
    }
    static int[] values(ListNode node) {
        List<Integer> values=new ArrayList<>();
        for(int steps=0;node!=null;node=node.next) {
            if(steps++>10000)throw new AssertionError("Unexpected list cycle");
            values.add(node.val);
        }
        return values.stream().mapToInt(Integer::intValue).toArray();
    }
    static TreeNode tree(Integer... values) {
        if(values.length==0 || values[0]==null)return null;
        TreeNode root=new TreeNode(values[0]); Queue<TreeNode> q=new ArrayDeque<>();q.add(root);int i=1;
        while(!q.isEmpty() && i<values.length) {
            TreeNode node=q.remove();
            if(values[i]!=null){node.left=new TreeNode(values[i]);q.add(node.left);}i++;
            if(i<values.length){if(values[i]!=null){node.right=new TreeNode(values[i]);q.add(node.right);}i++;}
        }
        return root;
    }
    static List<Integer> inorder(TreeNode root) {
        List<Integer> out=new ArrayList<>(); Deque<TreeNode> stack=new ArrayDeque<>();
        while(root!=null || !stack.isEmpty()){
            while(root!=null){stack.push(root);root=root.left;}
            root=stack.pop();out.add(root.val);root=root.right;
        }
        return out;
    }
}
