// Local checks for LeetCode 721.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        List<List<String>> input=Arrays.asList(Arrays.asList("John","johnsmith@mail.com","john_newyork@mail.com"),
        Arrays.asList("John","johnsmith@mail.com","john00@mail.com"),Arrays.asList("Mary","mary@mail.com"),Arrays.asList("John","johnnybravo@mail.com"));
        Checks.eq(new HashSet<>(Arrays.asList(Arrays.asList("John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"),
        Arrays.asList("Mary","mary@mail.com"),Arrays.asList("John","johnnybravo@mail.com"))),new HashSet<>(s.accountsMerge(input)));
        Checks.eq(2,s.accountsMerge(Arrays.asList(Arrays.asList("Alex","a@mail.com"),Arrays.asList("Alex","b@mail.com"))).size());
        return Checks.count;
    }
}

