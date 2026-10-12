// Local checks for LeetCode 767.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        Checks.eq("",s.reorganizeString("aaab"));
        for (String input:Arrays.asList("aab","vvvlo","a","aabbccdde","aaabbbcc")) {
            String out=s.reorganizeString(input);
            Checks.eq(input.length(),out.length());
            char[] a=input.toCharArray(),b=out.toCharArray(); Arrays.sort(a); Arrays.sort(b); Checks.eq(a,b);
            for (int i=1;i<out.length();i++) Checks.eq(false,out.charAt(i)==out.charAt(i-1));
        }
        return Checks.count;
    }
}

