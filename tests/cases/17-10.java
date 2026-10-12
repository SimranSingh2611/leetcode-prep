// Local checks for LeetCode 745.
import java.util.*;
class Case {
    public static int run() {
        WordFilter s=new WordFilter(new String[]{"apple"}); Checks.eq(0,s.f("a","e")); Checks.eq(-1,s.f("b","e"));
        WordFilter d=new WordFilter(new String[]{"apple","apply","apple","ape"});
        Checks.eq(3,d.f("a","e")); Checks.eq(2,d.f("appl","e")); Checks.eq(1,d.f("ap","ly"));
        Checks.eq(-1,d.f("apple","z"));
        String[] words={"ab","abc","aab","cab","b","ab"}; WordFilter all=new WordFilter(words);
        for (String pref:Arrays.asList("a","ab","b","c","z"))
            for (String suff:Arrays.asList("b","ab","c","z")) {
                int expected=-1; for (int i=0;i<words.length;i++) if (words[i].startsWith(pref) && words[i].endsWith(suff)) expected=i;
                Checks.eq(expected,all.f(pref,suff));
        }
        return Checks.count;
    }
}

