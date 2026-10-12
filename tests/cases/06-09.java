// Focused regression checks for binary-search boundary templates.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        Checks.close(2,s.findMedianSortedArrays(new int[]{1,3},new int[]{2}));
        Checks.close(2.5,s.findMedianSortedArrays(new int[]{1,2},new int[]{3,4}));
        Checks.close(3.5,s.findMedianSortedArrays(new int[]{3,4},new int[]{1,2,5,6}));
        Checks.close(Integer.MAX_VALUE,s.findMedianSortedArrays(new int[]{Integer.MAX_VALUE},new int[]{Integer.MAX_VALUE}));
        Checks.close(-0.5,s.findMedianSortedArrays(new int[]{Integer.MIN_VALUE},new int[]{Integer.MAX_VALUE}));
        Checks.close(Integer.MIN_VALUE,s.findMedianSortedArrays(new int[]{},new int[]{Integer.MIN_VALUE}));
        List<int[]> arrays=new ArrayList<>();arrays.add(new int[]{});
        for(int length=1;length<=5;length++){
            int combinations=1;for(int i=0;i<length;i++)combinations*=5;
            for(int code=0;code<combinations;code++){
                int[] a=new int[length];int remaining=code;boolean sorted=true;
                for(int i=0;i<length;i++){a[i]=remaining%5-2;remaining/=5;if(i>0 && a[i]<a[i-1])sorted=false;}
                if(sorted)arrays.add(a);
            }
        }
        for(int[] a:arrays)for(int[] b:arrays){
            if(a.length+b.length==0)continue;
            int[] merged=Arrays.copyOf(a,a.length+b.length);System.arraycopy(b,0,merged,a.length,b.length);Arrays.sort(merged);
            int n=merged.length;double expected=n%2==1?merged[n/2]:((long)merged[n/2-1]+merged[n/2])/2.0;
            Checks.close(expected,s.findMedianSortedArrays(a,b));
        }
        Random rng=new Random(6004);
        for(int t=0;t<500;t++){
            int[] a=new int[rng.nextInt(50)],b=new int[1+rng.nextInt(50)];
            for(int i=0;i<a.length;i++)a[i]=rng.nextInt();
            for(int i=0;i<b.length;i++)b[i]=rng.nextInt();
            Arrays.sort(a);Arrays.sort(b);int[] ac=a.clone(),bc=b.clone();
            int[] merged=Arrays.copyOf(a,a.length+b.length);System.arraycopy(b,0,merged,a.length,b.length);Arrays.sort(merged);
            int n=merged.length;double expected=n%2==1?merged[n/2]:((long)merged[n/2-1]+merged[n/2])/2.0;
            Checks.close(expected,s.findMedianSortedArrays(a,b));Checks.eq(ac,a);Checks.eq(bc,b);
        }
        return Checks.count;
    }
}
