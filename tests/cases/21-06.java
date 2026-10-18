// Local checks for LeetCode 729: online acceptance against direct interval intersections.
import java.util.*;
class Case {
    public static int run() {
        MyCalendar calendar=new MyCalendar();
        Checks.eq(true,calendar.book(10,20));
        Checks.eq(false,calendar.book(15,25));
        Checks.eq(true,calendar.book(20,30));
        Checks.eq(false,calendar.book(10,20));
        Checks.eq(false,calendar.book(12,18));
        Checks.eq(false,calendar.book(5,35));
        Checks.eq(true,calendar.book(0,10));
        Checks.eq(true,calendar.book(30,40));

        MyCalendar gaps=new MyCalendar();
        Checks.eq(true,gaps.book(20,30));
        Checks.eq(true,gaps.book(0,10));
        Checks.eq(false,gaps.book(15,25)); // Successor conflict; rejection must preserve the gap.
        Checks.eq(false,gaps.book(5,15));  // Predecessor conflict.
        Checks.eq(true,gaps.book(10,20));

        MyCalendar limits=new MyCalendar();
        Checks.eq(true,limits.book(999999999,1000000000));
        Checks.eq(true,limits.book(0,999999999));
        Checks.eq(false,limits.book(0,1000000000));
        Checks.eq(true,new MyCalendar().book(0,1000000000)); // Instances are independent.

        Random rng=new Random(729);
        for(int trial=0;trial<100;trial++) {
            MyCalendar actual=new MyCalendar();
            List<int[]> accepted=new ArrayList<>();
            for(int call=0;call<100;call++) {
                int start=rng.nextInt(40),end=start+1+rng.nextInt(12);
                boolean expected=true;
                for(int[] booking:accepted) {
                    if(start<booking[1] && booking[0]<end) {expected=false;break;}
                }
                Checks.eq(expected,actual.book(start,end));
                if(expected) accepted.add(new int[]{start,end});
            }
        }

        MyCalendar many=new MyCalendar();
        for(int i=0;i<1000;i++) Checks.eq(true,many.book(i,i+1));
        return Checks.count;
    }
}
