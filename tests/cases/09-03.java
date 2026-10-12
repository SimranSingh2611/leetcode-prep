// Nearest strictly smaller indices: examples, duplicates, empty input, and exhaustive arrays.
import java.util.*;
class Case {
    public static int run() {
        check(new int[]{13,8,1,5,2,5,9,7,6,12});
        check(new int[]{Integer.MIN_VALUE,0,Integer.MAX_VALUE,0,Integer.MIN_VALUE});
        for (int length = 0; length <= 6; length++) {
            int combinations = 1;
            for (int i = 0; i < length; i++) combinations *= 4;
            for (int code = 0; code < combinations; code++) {
                int[] values = new int[length];
                int remainder = code;
                for (int i = 0; i < length; i++) { values[i] = remainder % 4 - 2; remainder /= 4; }
                check(values);
            }
        }
        return Checks.count;
    }

    private static void check(int[] values) {
        int[] expected = new int[values.length], original = values.clone();
        Arrays.fill(expected, -1);
        for (int i = 0; i < values.length; i++) {
            for (int candidate = i + (1); candidate >= 0 && candidate < values.length; candidate += 1) {
                if (values[candidate] < values[i]) {
                    expected[i] = candidate;
                    break;
                }
            }
        }
        Checks.eq(expected, new Solution().nextSmallerIndexes(values));
        Checks.eq(original, values);
    }
}
