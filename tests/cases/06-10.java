// Focused regression checks for binary-search boundary templates.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        Checks.eq(18,s.splitArray(new int[]{7,2,5,10,8},2));
        Checks.eq(9,s.splitArray(new int[]{1,2,3,4,5},2));
        Checks.eq(0,s.splitArray(new int[]{0,0,0},1));
        Checks.eq(0,s.splitArray(new int[]{0,0,0},3));
        Checks.eq(10,s.splitArray(new int[]{1,2,3,4},1));
        Checks.eq(4,s.splitArray(new int[]{1,2,3,4},4));
        int[] large=new int[1000];Arrays.fill(large,1000000);Checks.eq(1000000000,s.splitArray(large,1));
        Random rng=new Random(6410);
        for(int n=1;n<=8;n++)for(int t=0;t<80;t++){
            int[] a=new int[n];for(int i=0;i<n;i++)a[i]=rng.nextInt(10);
            for(int k=1;k<=n;k++){
                int best=Integer.MAX_VALUE;
                for(int mask=0;mask<(1<<(n-1));mask++){
                    if(Integer.bitCount(mask)!=k-1)continue;
                    int sum=0,maximum=0;
                    for(int i=0;i<n;i++){
                        sum+=a[i];
                        if(i==n-1 || (mask&(1<<i))!=0){maximum=Math.max(maximum,sum);sum=0;}
                    }
                    best=Math.min(best,maximum);
                }
                Checks.eq(best,s.splitArray(a,k));
            }
        }
        return Checks.count;
    }
}
