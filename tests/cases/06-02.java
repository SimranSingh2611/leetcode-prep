// Focused regression checks for binary-search boundary templates.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        Checks.eq(2,s.searchInsert(new int[]{1,3,5,6},5));
        Checks.eq(1,s.searchInsert(new int[]{1,3,5,6},2));
        Checks.eq(4,s.searchInsert(new int[]{1,3,5,6},7));
        Checks.eq(0,s.searchInsert(new int[]{1,3,5,6},0));
        Checks.eq(0,s.searchInsert(new int[]{},8));
        Random rng=new Random(635);
        for(int t=0;t<300;t++){
            int[] a=new int[rng.nextInt(40)];for(int i=0;i<a.length;i++)a[i]=rng.nextInt(50)-25;
            Arrays.sort(a);
            for(int target=-30;target<=30;target++){
                int expected=0;while(expected<a.length && a[expected]<target)expected++;
                Checks.eq(expected,s.searchInsert(a,target));
            }
        }
        return Checks.count;
    }
}
