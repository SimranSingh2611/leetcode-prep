// Local checks for LeetCode 907.
import java.util.*;
class Case {
    public static int run() {
        Checks.eq(17,new Solution().sumSubarrayMins(new int[]{3,1,2,4}));
        Checks.eq(6,new Solution().sumSubarrayMins(new int[]{2,2}));
        Random r=new Random(907);
        for(int t=0;t<100;t++){int[] a=new int[8];for(int i=0;i<a.length;i++)a[i]=r.nextInt(8)+1;int total=0;
        for(int i=0;i<a.length;i++){int min=Integer.MAX_VALUE;for(int j=i;j<a.length;j++){min=Math.min(min,a[j]);total+=min;}}
        Checks.eq(total,new Solution().sumSubarrayMins(a));}
        return Checks.count;
    }
}

