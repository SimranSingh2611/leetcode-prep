// Local checks for LeetCode 2402: heap scheduling against a direct room-availability scan.
import java.util.*;
class Case {
    public static int run() {
        Solution s=new Solution();
        Checks.eq(0,s.mostBooked(2,new int[][]{{0,10},{1,5},{2,7},{3,4}}));
        Checks.eq(1,s.mostBooked(3,new int[][]{{1,20},{2,10},{3,5},{4,9},{6,8}}));
        Checks.eq(0,s.mostBooked(1,new int[][]{{5,9},{0,7},{2,6}}));
        Checks.eq(0,s.mostBooked(100,new int[][]{{0,1}}));
        Checks.eq(0,s.mostBooked(2,new int[][]{{0,10},{1,2}})); // Usage-count tie.
        // Several rooms are free by time 6; choose the lowest ID, not earliest former finish.
        Checks.eq(0,s.mostBooked(3,new int[][]{{0,5},{1,2},{2,3},{6,7},{7,8}}));
        Checks.eq(0,s.mostBooked(2,new int[][]{{0,5},{1,5},{5,6},{6,7}}));

        int[][] original={{4,9},{1,20},{6,8},{3,5},{2,10}};
        Checks.eq(1,s.mostBooked(3,original));
        Checks.eq(new int[][]{{4,9},{1,20},{6,8},{3,5},{2,10}},original);

        Random rng=new Random(2402);
        for(int trial=0;trial<500;trial++) {
            int rooms=1+rng.nextInt(6),m=1+rng.nextInt(30);
            int[][] meetings=new int[m][2];
            for(int i=0;i<m;i++) {
                meetings[i][0]=2*i;
                meetings[i][1]=2*i+1+rng.nextInt(40);
            }
            for(int i=m-1;i>0;i--) {
                int j=rng.nextInt(i+1);
                int[] tmp=meetings[i];meetings[i]=meetings[j];meetings[j]=tmp;
            }
            Checks.eq(reference(rooms,meetings),s.mostBooked(rooms,meetings));
        }

        // All input endpoints meet the statement's bounds, but delayed finishes exceed 2^31-1.
        // Using int for those finishes makes room 1 win; the correct winner is room 0.
        int[][] overflow=new int[20000][2];
        for(int i=0;i<overflow.length;i++) {
            overflow[i][0]=i;
            overflow[i][1]=i+(i<1000 ? 1 : i==1000 ? 450000 : 400000);
        }
        Checks.eq(0,reference(2,overflow));
        Checks.eq(0,s.mostBooked(2,overflow));
        return Checks.count;
    }

    private static int reference(int rooms,int[][] meetings) {
        int[][] ordered=meetings.clone();
        Arrays.sort(ordered,(a,b)->Integer.compare(a[0],b[0]));
        long[] freeAt=new long[rooms];
        int[] counts=new int[rooms];
        for(int[] meeting:ordered) {
            int chosen=-1;
            // Scan IDs to find the lowest currently free room.
            for(int room=0;room<rooms;room++) {
                if(freeAt[room]<=meeting[0]) {chosen=room;break;}
            }
            if(chosen<0) {
                chosen=0;
                for(int room=1;room<rooms;room++) {
                    if(freeAt[room]<freeAt[chosen]) chosen=room;
                }
            }
            long start=Math.max(freeAt[chosen],(long)meeting[0]);
            freeAt[chosen]=start+(long)meeting[1]-meeting[0];
            counts[chosen]++;
        }
        int best=0;
        for(int room=1;room<rooms;room++) if(counts[room]>counts[best]) best=room;
        return best;
    }
}
