// Water: independent per-column left/right maximum oracle.
import java.util.*;
class Case {
    public static int run() {
        Solution s = new Solution();
        Checks.eq(6, s.trap(new int[]{0,1,0,2,1,0,1,3,2,1,2,1}));
        Checks.eq(9, s.trap(new int[]{4,2,0,3,2,5}));
        Checks.eq(0, s.trap(new int[]{3,3,3}));
        Checks.eq(3, s.trap(new int[]{3,0,3}));
        Random random = new Random(42);
        for (int test = 0; test < 400; test++) {
            int[] heights = new int[1 + random.nextInt(20)];
            for (int i = 0; i < heights.length; i++) heights[i] = random.nextInt(10);
            int expected = 0;
            for (int i = 0; i < heights.length; i++) {
                int left = 0, right = 0;
                for (int j = 0; j <= i; j++) left = Math.max(left, heights[j]);
                for (int j = i; j < heights.length; j++) right = Math.max(right, heights[j]);
                expected += Math.min(left, right) - heights[i];
            }
            int[] original = heights.clone();
            Checks.eq(expected, s.trap(heights));
            Checks.eq(original, heights);
        }
        int[] large = new int[20000];
        large[0] = large[large.length - 1] = 100000;
        Checks.eq(1999800000, s.trap(large));
        return Checks.count;
    }
}
