// Local checks for LeetCode 253: compare with direct concurrency at every start.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        Checks.eq(2,s.minMeetingRooms(new int[][]{{0,30},{5,10},{15,20}}));
        Checks.eq(1,s.minMeetingRooms(new int[][]{{7,10},{2,4}}));
        Checks.eq(1,s.minMeetingRooms(new int[][]{{2,3},{1,2},{3,4}}));
        Checks.eq(3,s.minMeetingRooms(new int[][]{{1,9},{1,9},{1,9}}));
        Checks.eq(3,s.minMeetingRooms(new int[][]{{1,10},{2,9},{3,8}}));
        // The peak may occur before the final meeting; all expired rooms must be removed.
        Checks.eq(3,s.minMeetingRooms(new int[][]{{0,2},{0,3},{0,4},{10,11}}));
        Checks.eq(0,s.minMeetingRooms(new int[0][]));
        Checks.eq(1,s.minMeetingRooms(new int[][]{{1,2}}));
        Checks.eq(1,s.minMeetingRooms(new int[][]{{0,Integer.MAX_VALUE},{Integer.MIN_VALUE,0}}));
        int[][] original={{5,8},{1,3},{2,6}};
        Checks.eq(2,s.minMeetingRooms(original));
        Checks.eq(new int[][]{{5,8},{1,3},{2,6}},original);
        Random rng=new Random(253);
        for(int t=0;t<150;t++) {
            int[][] a=new int[rng.nextInt(12)][2];
            for(int[] row:a) {row[0]=rng.nextInt(20);row[1]=row[0]+1+rng.nextInt(8);}
            int peak=0;
            for(int[] event:a) {
                int active=0;
                for(int[] meeting:a) if(meeting[0]<=event[0] && event[0]<meeting[1]) active++;
                peak=Math.max(peak,active);
            }
            Checks.eq(peak,s.minMeetingRooms(a));
        }
        return Checks.count;
    }
}
