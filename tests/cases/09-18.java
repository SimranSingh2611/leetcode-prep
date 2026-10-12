// Local checks for LeetCode 85.
import java.util.*;
class Case {
    public static int run() {
        Checks.eq(6,new Solution().maximalRectangle(new char[][]{"10100".toCharArray(),"10111".toCharArray(),"11111".toCharArray(),"10010".toCharArray()}));
        Checks.eq(0,new Solution().maximalRectangle(new char[][]{{'0'}}));
        return Checks.count;
    }
}

