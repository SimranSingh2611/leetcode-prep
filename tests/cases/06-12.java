import java.util.*;
class Case {
    private static void check(Solution s, int[] a) {
        int[] before = a.clone();
        int peak = s.findPeakElement(a);
        Checks.eq(true, peak >= 0 && peak < a.length);
        Checks.eq(true, peak == 0 || a[peak] > a[peak-1]);
        Checks.eq(true, peak == a.length-1 || a[peak] > a[peak+1]);
        Checks.eq(before, a);
    }
    public static int run() {
        Solution s = new Solution();
        check(s, new int[]{1,2,3,1});
        check(s, new int[]{1,2,1,3,5,6,4});
        check(s, new int[]{Integer.MIN_VALUE});
        check(s, new int[]{Integer.MIN_VALUE,Integer.MAX_VALUE,Integer.MIN_VALUE});
        for (int n = 1; n <= 8; n++) {
            int combinations = 1;
            for (int i = 0; i < n; i++) combinations *= 3;
            for (int code = 0; code < combinations; code++) {
                int[] a = new int[n];
                int remaining = code;
                boolean valid = true;
                for (int i = 0; i < n; i++) {
                    a[i] = remaining % 3 - 1;
                    remaining /= 3;
                    if (i > 0 && a[i] == a[i-1]) valid = false;
                }
                if (valid) check(s, a);
            }
        }
        int[] rising = new int[1000], falling = new int[1000], alternating = new int[1000];
        for (int i = 0; i < rising.length; i++) {
            rising[i] = i;
            falling[i] = -i;
            alternating[i] = i % 2 == 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }
        Checks.eq(999, s.findPeakElement(rising));
        Checks.eq(0, s.findPeakElement(falling));
        check(s, alternating);
        return Checks.count;
    }
}
