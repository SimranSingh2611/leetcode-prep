// Local checks for LeetCode 252: compare with all-pairs conflict detection.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        Checks.eq(false,s.canAttendMeetings(new int[][]{{0,30},{5,10},{15,20}}));
        Checks.eq(true,s.canAttendMeetings(new int[][]{{7,10},{2,4}}));
        Checks.eq(true,s.canAttendMeetings(new int[][]{{2,3},{1,2},{3,4}}));
        Checks.eq(false,s.canAttendMeetings(new int[][]{{1,10},{2,3}}));
        Checks.eq(false,s.canAttendMeetings(new int[][]{{1,2},{1,2}}));
        Checks.eq(true,s.canAttendMeetings(new int[0][]));
        Checks.eq(true,s.canAttendMeetings(new int[][]{{1,2}}));
        Checks.eq(true,s.canAttendMeetings(new int[][]{{0,Integer.MAX_VALUE},{Integer.MIN_VALUE,0}}));
        int[][] original={{5,8},{1,3}};
        Checks.eq(true,s.canAttendMeetings(original));
        Checks.eq(new int[][]{{5,8},{1,3}},original);
        Random rng=new Random(252);
        for(int t=0;t<150;t++) {
            int[][] a=new int[rng.nextInt(12)][2];
            for(int[] row:a) {row[0]=rng.nextInt(20);row[1]=row[0]+1+rng.nextInt(8);}
            boolean possible=true;
            for(int i=0;i<a.length;i++) for(int j=i+1;j<a.length;j++)
                if(a[i][0]<a[j][1] && a[j][0]<a[i][1]) possible=false;
            Checks.eq(possible,s.canAttendMeetings(a));
        }
        return Checks.count;
    }
}
