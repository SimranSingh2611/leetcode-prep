import java.util.*;
class Case {
    public static int run() {
        Solution s = new Solution();
        Checks.eq(2, s.singleNonDuplicate(new int[]{1,1,2,3,3,4,4,8,8}));
        Checks.eq(10, s.singleNonDuplicate(new int[]{3,3,7,7,10,11,11}));
        for (int pairs = 0; pairs <= 80; pairs++) {
            for (int unique = 0; unique <= pairs; unique++) {
                int[] a = new int[2*pairs+1];
                int index = 0, expected = 0;
                for (int i = 0; i <= pairs; i++) {
                    int value = i == 0 ? Integer.MIN_VALUE : i == pairs ? Integer.MAX_VALUE : i*3;
                    a[index++] = value;
                    if (i == unique) expected = value;
                    else a[index++] = value;
                }
                int[] before = a.clone();
                Checks.eq(expected, s.singleNonDuplicate(a));
                Checks.eq(before, a);
            }
        }
        Checks.eq(Integer.MAX_VALUE, s.singleNonDuplicate(new int[]{Integer.MAX_VALUE}));
        return Checks.count;
    }
}
