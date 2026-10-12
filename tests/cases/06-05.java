// Focused regression checks for binary-search boundary templates.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        Checks.eq(4,s.minEatingSpeed(new int[]{3,6,7,11},8));
        Checks.eq(30,s.minEatingSpeed(new int[]{30,11,23,4,20},5));
        Checks.eq(23,s.minEatingSpeed(new int[]{30,11,23,4,20},6));
        Checks.eq(1,s.minEatingSpeed(new int[]{1},1));
        Checks.eq(4,s.minEatingSpeed(new int[]{1000000000,1000000000,1000000000},1000000000));
        Checks.eq(1000000000,s.minEatingSpeed(new int[]{1000000000,1000000000,1000000000},3));
        Random rng=new Random(6875);
        for(int t=0;t<500;t++){
            int[] piles=new int[1+rng.nextInt(8)];int max=0;
            for(int i=0;i<piles.length;i++){piles[i]=1+rng.nextInt(30);max=Math.max(max,piles[i]);}
            int h=piles.length+rng.nextInt(60),expected=-1;
            for(int speed=1;speed<=max;speed++){
                long hours=0;for(int pile:piles)hours+=(pile+speed-1)/speed;
                if(hours<=h){expected=speed;break;}
            }
            int[] before=piles.clone();Checks.eq(expected,s.minEatingSpeed(piles,h));Checks.eq(before,piles);
        }
        return Checks.count;
    }
}
