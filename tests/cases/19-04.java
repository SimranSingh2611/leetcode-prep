// Local checks for LeetCode 2115.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        Checks.eq(Arrays.asList("bread","sandwich"),s.findAllRecipes(new String[]{"bread","sandwich"},
        Arrays.asList(Arrays.asList("yeast","flour"),Arrays.asList("bread","meat")),new String[]{"yeast","flour","meat"}));
        Checks.eq(Collections.emptyList(),s.findAllRecipes(new String[]{"bread"},Arrays.asList(Arrays.asList("yeast","flour")),new String[]{"yeast"}));
        Checks.eq(Collections.emptyList(),s.findAllRecipes(new String[]{"a","b"},Arrays.asList(Arrays.asList("b"),Arrays.asList("a")),new String[]{"x"}));
        return Checks.count;
    }
}

