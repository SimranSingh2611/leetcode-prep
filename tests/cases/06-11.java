import java.util.*;
class Case {
    public static int run() {
        Solution s = new Solution();
        Checks.eq(new int[]{3,4}, s.searchRange(new int[]{5,7,7,8,8,10}, 8));
        Checks.eq(new int[]{-1,-1}, s.searchRange(new int[]{5,7,7,8,8,10}, 6));
        Checks.eq(new int[]{-1,-1}, s.searchRange(new int[]{}, 0));
        Checks.eq(new int[]{0,2}, s.searchRange(new int[]{2,2,2}, 2));
        Checks.eq(new int[]{0,1}, s.searchRange(
                new int[]{Integer.MIN_VALUE,Integer.MIN_VALUE,Integer.MAX_VALUE}, Integer.MIN_VALUE));
        Checks.eq(new int[]{1,2}, s.searchRange(
                new int[]{Integer.MIN_VALUE,Integer.MAX_VALUE,Integer.MAX_VALUE}, Integer.MAX_VALUE));
        Random rng = new Random(6034);
        for (int t = 0; t < 500; t++) {
            int[] a = new int[rng.nextInt(40)];
            for (int i = 0; i < a.length; i++) a[i] = rng.nextInt(21)-10;
            Arrays.sort(a);
            int[] before = a.clone();
            for (int target = -12; target <= 12; target++) {
                int first = -1, last = -1;
                for (int i = 0; i < a.length; i++) {
                    if (a[i] == target) {
                        if (first == -1) first = i;
                        last = i;
                    }
                }
                Checks.eq(new int[]{first,last}, s.searchRange(a, target));
            }
            Checks.eq(before, a);
        }
        return Checks.count;
    }
}
