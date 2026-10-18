// Local checks for LeetCode 731: rejection rollback and a direct small-timeline oracle.
import java.util.*;
class Case {
    public static int run() {
        MyCalendarTwo calendar=new MyCalendarTwo();
        int[][] example={{10,20},{50,60},{10,40},{5,15},{5,10},{25,55}};
        boolean[] expected={true,true,true,false,true,true};
        for(int i=0;i<example.length;i++) {
            Checks.eq(expected[i],calendar.book(example[i][0],example[i][1]));
        }

        MyCalendarTwo rollback=new MyCalendarTwo();
        Checks.eq(true,rollback.book(10,20));
        Checks.eq(true,rollback.book(10,20));
        Checks.eq(false,rollback.book(5,25));
        Checks.eq(false,rollback.book(5,25));
        Checks.eq(true,rollback.book(0,10)); // Rejected left endpoint must not remain.
        Checks.eq(true,rollback.book(20,30)); // Rejected right endpoint must not remain.
        Checks.eq(true,rollback.book(0,10));
        Checks.eq(false,rollback.book(0,10));
        Checks.eq(true,rollback.book(20,30));
        Checks.eq(false,rollback.book(10,20));

        MyCalendarTwo limits=new MyCalendarTwo();
        Checks.eq(true,limits.book(0,1000000000));
        Checks.eq(true,limits.book(0,1));
        Checks.eq(true,limits.book(999999999,1000000000));
        Checks.eq(false,limits.book(0,1000000000));
        Checks.eq(true,limits.book(1,999999999));
        Checks.eq(true,new MyCalendarTwo().book(0,1000000000));

        Random rng=new Random(731);
        for(int trial=0;trial<100;trial++) {
            MyCalendarTwo actual=new MyCalendarTwo();
            int[] occupancy=new int[40];
            for(int call=0;call<100;call++) {
                int start=rng.nextInt(40),end=start+1+rng.nextInt(40-start);
                boolean valid=true;
                for(int t=start;t<end;t++) if(occupancy[t]>=2) {valid=false;break;}
                Checks.eq(valid,actual.book(start,end));
                if(valid) for(int t=start;t<end;t++) occupancy[t]++;
            }
        }

        MyCalendarTwo repeated=new MyCalendarTwo();
        for(int i=0;i<1000;i++) Checks.eq(i<2,repeated.book(0,1000000000));
        return Checks.count;
    }
}
