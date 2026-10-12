// Local checks for LeetCode 355.
import java.util.*;
class Case {
    public static int run() {
        Twitter s=new Twitter(); Checks.eq(Collections.emptyList(),s.getNewsFeed(1));
        s.postTweet(1,5); Checks.eq(Arrays.asList(5),s.getNewsFeed(1));
        s.follow(1,2); s.postTweet(2,6); Checks.eq(Arrays.asList(6,5),s.getNewsFeed(1));
        s.unfollow(1,2); Checks.eq(Arrays.asList(5),s.getNewsFeed(1));
        s.follow(1,1); Checks.eq(Arrays.asList(5),s.getNewsFeed(1));
        for (int i=10;i<30;i++) s.postTweet(1,i);
        Checks.eq(Arrays.asList(29,28,27,26,25,24,23,22,21,20),s.getNewsFeed(1));
        return Checks.count;
    }
}

