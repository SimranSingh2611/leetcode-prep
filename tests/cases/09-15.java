// Visibility: shuffled distinct heights, checked against every intervening person.
import java.util.*;
class Case {
    public static int run() {
        Solution s = new Solution();
        Checks.eq(new int[]{3,1,2,1,1,0}, s.canSeePersonsCount(new int[]{10,6,8,5,11,9}));
        Checks.eq(new int[]{4,1,1,1,0}, s.canSeePersonsCount(new int[]{5,1,2,3,10}));
        Random random = new Random(1944);
        for (int test = 0; test < 400; test++) {
            int[] heights = new int[1 + random.nextInt(20)];
            for (int i = 0; i < heights.length; i++) heights[i] = i + 1;
            for (int i = heights.length - 1; i > 0; i--) {
                int j = random.nextInt(i + 1), temp = heights[i];
                heights[i] = heights[j]; heights[j] = temp;
            }
            int[] expected = new int[heights.length], original = heights.clone();
            for (int i = 0; i < heights.length; i++) {
                for (int j = i + 1; j < heights.length; j++) {
                    boolean visible = true;
                    for (int k = i + 1; k < j; k++)
                        if (heights[k] >= Math.min(heights[i], heights[j])) visible = false;
                    if (visible) expected[i]++;
                }
            }
            Checks.eq(expected, s.canSeePersonsCount(heights));
            Checks.eq(original, heights);
        }
        return Checks.count;
    }
}
