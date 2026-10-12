// Focused regression checks for binary-search boundary templates.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        Checks.eq(4,s.search(new int[]{-1,0,3,5,9,12},9));
        Checks.eq(-1,s.search(new int[]{-1,0,3,5,9,12},2));
        Checks.eq(-1,s.search(new int[]{},5));
        Checks.eq(0,s.search(new int[]{Integer.MIN_VALUE,0,Integer.MAX_VALUE},Integer.MIN_VALUE));
        Checks.eq(2,s.search(new int[]{Integer.MIN_VALUE,0,Integer.MAX_VALUE},Integer.MAX_VALUE));
        Random rng=new Random(604);
        for(int t=0;t<300;t++){
            int[] a=new int[rng.nextInt(40)];int value=-60;
            for(int i=0;i<a.length;i++){value+=rng.nextInt(4);a[i]=value;}
            int[] before=a.clone();
            for(int target=-65;target<70;target++){
                int expected=-1;for(int i=0;i<a.length;i++)if(a[i]==target){expected=i;break;}
                Checks.eq(expected,s.search(a,target));
            }
            Checks.eq(before,a);
        }
        return Checks.count;
    }
}
