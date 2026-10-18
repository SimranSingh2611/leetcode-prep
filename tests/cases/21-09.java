// Local checks for LeetCode 732: running maximum against direct small-timeline occupancy.
import java.util.*;
class Case {
    public static int run() {
        MyCalendarThree calendar=new MyCalendarThree();
        int[][] example={{10,20},{50,60},{10,40},{5,15},{5,10},{25,55}};
        int[] expected={1,1,2,3,3,3};
        for(int i=0;i<example.length;i++) {
            Checks.eq(expected[i],calendar.book(example[i][0],example[i][1]));
        }

        MyCalendarThree touching=new MyCalendarThree();
        Checks.eq(1,touching.book(10,20));
        Checks.eq(1,touching.book(20,30));
        Checks.eq(1,touching.book(0,10));
        Checks.eq(2,touching.book(0,30));
        Checks.eq(3,touching.book(0,30));
        Checks.eq(3,touching.book(40,50)); // Earlier peak still determines the answer.

        MyCalendarThree limits=new MyCalendarThree();
        Checks.eq(1,limits.book(0,1000000000));
        Checks.eq(2,limits.book(0,1));
        Checks.eq(2,limits.book(999999999,1000000000));
        Checks.eq(2,limits.book(1,999999999));
        Checks.eq(3,limits.book(0,1000000000));
        Checks.eq(1,new MyCalendarThree().book(0,1000000000));

        Random rng=new Random(732);
        for(int trial=0;trial<100;trial++) {
            MyCalendarThree actual=new MyCalendarThree();
            int[] occupancy=new int[40];
            for(int call=0;call<100;call++) {
                int start=rng.nextInt(40),end=start+1+rng.nextInt(40-start);
                for(int t=start;t<end;t++) occupancy[t]++;
                int peak=0;
                for(int count:occupancy) peak=Math.max(peak,count);
                Checks.eq(peak,actual.book(start,end));
            }
        }

        MyCalendarThree repeated=new MyCalendarThree();
        for(int i=1;i<=400;i++) Checks.eq(i,repeated.book(0,1000000000));
        return Checks.count;
    }
}
