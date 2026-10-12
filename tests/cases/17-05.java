// Local checks for LeetCode 1268.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        Checks.eq(Arrays.asList(Arrays.asList("mobile","moneypot","monitor"),Arrays.asList("mobile","moneypot","monitor"),
        Arrays.asList("mouse","mousepad"),Arrays.asList("mouse","mousepad"),Arrays.asList("mouse","mousepad")),
        s.suggestedProducts(new String[]{"mobile","mouse","moneypot","monitor","mousepad"},"mouse"));
        Checks.eq(Arrays.asList(Collections.emptyList(),Collections.emptyList()),s.suggestedProducts(new String[]{"a"},"zz"));
        return Checks.count;
    }
}

