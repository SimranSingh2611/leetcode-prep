// Local checks for LeetCode 84.
import java.util.*;
class Case {
    public static int run() {
        Checks.eq(10,new Solution().largestRectangleArea(new int[]{2,1,5,6,2,3}));
        Checks.eq(4,new Solution().largestRectangleArea(new int[]{2,2}));
        Random r=new Random(84);for(int t=0;t<100;t++){int[] a=new int[8];for(int i=0;i<8;i++)a[i]=r.nextInt(8);
        int best=0;for(int i=0;i<8;i++){int min=100;for(int j=i;j<8;j++){min=Math.min(min,a[j]);best=Math.max(best,min*(j-i+1));}}
        Checks.eq(best,new Solution().largestRectangleArea(a));}
        return Checks.count;
    }
}

