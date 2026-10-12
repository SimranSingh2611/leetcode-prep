// Local checks for LeetCode 134.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        Checks.eq(3,s.canCompleteCircuit(new int[]{1,2,3,4,5},new int[]{3,4,5,1,2}));
        Checks.eq(-1,s.canCompleteCircuit(new int[]{2,3,4},new int[]{3,4,3}));
        Checks.eq(0,s.canCompleteCircuit(new int[]{5},new int[]{4}));
        Random rng=new Random(134);
        for (int t=0;t<100;t++) {
            int n=1+rng.nextInt(10); int[] gas=new int[n],cost=new int[n]; int total=0;
            for (int i=0;i<n;i++) { gas[i]=rng.nextInt(8); cost[i]=rng.nextInt(8); total+=gas[i]-cost[i]; }
            int start=s.canCompleteCircuit(gas,cost);
            if (total<0) Checks.eq(-1,start);
            else {
                Checks.eq(true,start>=0 && start<n); int tank=0;
                for (int i=0;i<n;i++) { int p=(start+i)%n; tank+=gas[p]-cost[p]; Checks.eq(true,tank>=0); }
            }
        }
        return Checks.count;
    }
}

