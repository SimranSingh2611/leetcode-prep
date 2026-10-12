// Focused regression checks for binary-search boundary templates.
import java.util.*;
class VersionControl {
    static int limit,first,calls;
    protected boolean isBadVersion(int version) {
        Checks.eq(true,version>=1 && version<=limit);
        calls++;
        return version>=first;
    }
}
class Case {
    public static int run() {
        Solution s=new Solution();
        for(int n=1;n<=100;n++)for(int first=1;first<=n;first++){
            VersionControl.limit=n;VersionControl.first=first;VersionControl.calls=0;
            Checks.eq(first,s.firstBadVersion(n));
            Checks.eq(true,VersionControl.calls<=32-Integer.numberOfLeadingZeros(n));
        }
        for(int first:new int[]{1,2,1073741824,Integer.MAX_VALUE-1,Integer.MAX_VALUE}){
            VersionControl.limit=Integer.MAX_VALUE;VersionControl.first=first;VersionControl.calls=0;
            Checks.eq(first,s.firstBadVersion(Integer.MAX_VALUE));
            Checks.eq(true,VersionControl.calls<=31);
        }
        return Checks.count;
    }
}
