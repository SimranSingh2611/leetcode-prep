// Local checks for LeetCode 212.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        char[][] b={"oaan".toCharArray(),"etae".toCharArray(),"ihkr".toCharArray(),"iflv".toCharArray()};
        char[][] before={"oaan".toCharArray(),"etae".toCharArray(),"ihkr".toCharArray(),"iflv".toCharArray()};
        Checks.eq(new HashSet<>(Arrays.asList("eat","oath")),new HashSet<>(s.findWords(b,new String[]{"oath","pea","eat","rain"})));
        Checks.eq(before,b);
        List<String> out=s.findWords(new char[][]{{'a','a'}},new String[]{"a","aa","aaa"});
        Checks.eq(new HashSet<>(Arrays.asList("a","aa")),new HashSet<>(out)); Checks.eq(2,out.size());
        return Checks.count;
    }
}

