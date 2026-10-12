// Focused regression checks for binary-search boundary templates.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        Checks.eq(1,s.findMin(new int[]{3,4,5,1,2}));
        Checks.eq(0,s.findMin(new int[]{4,5,6,7,0,1,2}));
        Checks.eq(11,s.findMin(new int[]{11,13,15,17}));
        for(int n=1;n<=80;n++)for(int offset=0;offset<n;offset++){
            int[] a=new int[n];for(int i=0;i<n;i++)a[i]=((i+offset)%n)*3-100;
            int[] before=a.clone();Checks.eq(-100,s.findMin(a));Checks.eq(before,a);
        }
        Checks.eq(Integer.MIN_VALUE,s.findMin(new int[]{Integer.MAX_VALUE,Integer.MIN_VALUE,0}));
        return Checks.count;
    }
}
