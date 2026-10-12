// Local checks for LeetCode 853.
import java.util.*;
class Case {
    public static int run() {
        int[] p={10,8,0,5,3},v={2,4,1,1,3};
        Checks.eq(3,new Solution().carFleet(12,p,v)); Checks.eq(3,new CompressedSolution().carFleet(12,p,v));
        Checks.eq(1,new Solution().carFleet(12,new int[]{0,6},new int[]{2,1}));
        // Independent nearest-first oracle using exact fraction comparisons.
        Random random = new Random(853);
        for (int test = 0; test < 500; test++) {
            int n = 1 + random.nextInt(15), target = 100;
            int[] positions = new int[n], speeds = new int[n];
            for (int i = 0; i < n; i++) { positions[i] = i * 3; speeds[i] = 1 + random.nextInt(10); }
            long distance = -1, velocity = 1;
            int expected = 0;
            for (int i = n - 1; i >= 0; i--) {
                long nextDistance = target - positions[i];
                if (nextDistance * velocity > distance * speeds[i]) {
                    expected++; distance = nextDistance; velocity = speeds[i];
                }
            }
            // Shuffle input to ensure the implementation really sorts positions.
            for (int i = n - 1; i > 0; i--) {
                int j = random.nextInt(i + 1), temp = positions[i];
                positions[i] = positions[j]; positions[j] = temp;
                temp = speeds[i]; speeds[i] = speeds[j]; speeds[j] = temp;
            }
            int[] originalPositions = positions.clone(), originalSpeeds = speeds.clone();
            Checks.eq(expected, new Solution().carFleet(target, positions, speeds));
            Checks.eq(expected, new CompressedSolution().carFleet(target, positions, speeds));
            Checks.eq(originalPositions, positions); Checks.eq(originalSpeeds, speeds);
        }
        return Checks.count;
    }
}
