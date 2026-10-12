// Focused regression checks for binary-search boundary templates.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        Checks.eq(4,s.search(new int[]{4,5,6,7,0,1,2},0));
        Checks.eq(-1,s.search(new int[]{4,5,6,7,0,1,2},3));
        Checks.eq(-1,s.search(new int[]{1,2,3},4));
        Checks.eq(-1,s.search(new int[]{},4));
        for(int n=1;n<=30;n++)for(int offset=0;offset<n;offset++){
            int[] a=new int[n];for(int i=0;i<n;i++)a[i]=((i+offset)%n)*3-40;
            for(int target=-45;target<=n*3-35;target++){
                int expected=-1;for(int i=0;i<n;i++)if(a[i]==target)expected=i;
                Checks.eq(expected,s.search(a,target));
            }
        }
        Checks.eq(1,s.search(new int[]{Integer.MAX_VALUE,Integer.MIN_VALUE,0},Integer.MIN_VALUE));
        return Checks.count;
    }
}
