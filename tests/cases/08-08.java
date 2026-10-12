// Local checks for LeetCode 735.
import java.util.*;
class Case {
    public static int run() {
        Checks.eq(new int[]{5,10},new Solution().asteroidCollision(new int[]{5,10,-5}));
        Checks.eq(new int[]{},new Solution().asteroidCollision(new int[]{8,-8}));
        Checks.eq(new int[]{10},new Solution().asteroidCollision(new int[]{10,2,-5}));
        return Checks.count;
    }
}

