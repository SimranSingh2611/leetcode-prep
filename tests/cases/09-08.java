// Ocean views: compare against all buildings to the right, including equal blockers.
import java.util.*;
class Case {
    public static int run() {
        Solution s = new Solution();
        Checks.eq(new int[]{0,2,3}, s.findBuildings(new int[]{4,2,3,1}));
        Checks.eq(new int[]{2}, s.findBuildings(new int[]{2,2,2}));
        Random random = new Random(1762);
        for (int test = 0; test < 400; test++) {
            int[] heights = new int[1 + random.nextInt(15)];
            for (int i = 0; i < heights.length; i++) heights[i] = 1 + random.nextInt(8);
            int[] original = heights.clone(), expected = new int[heights.length];
            int size = 0;
            for (int i = 0; i < heights.length; i++) {
                boolean visible = true;
                for (int j = i + 1; j < heights.length; j++) {
                    if (heights[j] >= heights[i]) visible = false;
                }
                if (visible) expected[size++] = i;
            }
            Checks.eq(Arrays.copyOf(expected, size), s.findBuildings(heights));
            Checks.eq(original, heights);
        }
        return Checks.count;
    }
}
