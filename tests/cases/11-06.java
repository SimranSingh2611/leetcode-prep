// Local checks for LeetCode 1545.
import java.util.*;
class Case {
    public static int run() {
        Checks.eq('0',new Solution().findKthBit(3,1));Checks.eq('1',new Solution().findKthBit(4,11));
        String s="0";for(int n=1;n<=6;n++){if(n>1){StringBuilder b=new StringBuilder(s+"1");for(int i=s.length()-1;i>=0;i--)b.append(s.charAt(i)=='0'?'1':'0');s=b.toString();}
        for(int k=1;k<=s.length();k++)Checks.eq(s.charAt(k-1),new Solution().findKthBit(n,k));}
        return Checks.count;
    }
}

