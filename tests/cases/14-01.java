// Local checks for LeetCode 401.
import java.util.*;
class Case {
    public static int run() {
        Solution s = new Solution();
        Checks.eq(Arrays.asList("0:00"), s.readBinaryWatch(0));
        Checks.eq(10, s.readBinaryWatch(1).size());
        Checks.eq(0, s.readBinaryWatch(9).size());
        for (int on = 0; on <= 10; on++) {
            Set<String> expected = new HashSet<>();
            for (int h = 0; h < 12; h++) for (int m = 0; m < 60; m++)
                if (Integer.bitCount(h) + Integer.bitCount(m) == on)
                    expected.add(h + ":" + (m < 10 ? "0" : "") + m);
            Checks.eq(expected, new HashSet<>(s.readBinaryWatch(on)));
            Checks.eq(expected.size(), s.readBinaryWatch(on).size());
        }
        return Checks.count;
    }
}

