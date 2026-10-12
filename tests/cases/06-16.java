import java.util.*;
class Case {
    public static int run() {
        Solution s = new Solution();
        Checks.eq(true, s.isMajorityElement(new int[]{2,4,5,5,5,5,5,6,6}, 5));
        Checks.eq(false, s.isMajorityElement(new int[]{10,100,101,101}, 101));
        Checks.eq(false, s.isMajorityElement(new int[]{1,2,3}, 4));
        Checks.eq(false, s.isMajorityElement(new int[]{}, 1));
        Checks.eq(true, s.isMajorityElement(new int[]{Integer.MIN_VALUE,Integer.MIN_VALUE,0}, Integer.MIN_VALUE));
        Checks.eq(true, s.isMajorityElement(new int[]{0,Integer.MAX_VALUE,Integer.MAX_VALUE}, Integer.MAX_VALUE));
        Random rng = new Random(61150);
        for (int t = 0; t < 700; t++) {
            int[] a = new int[rng.nextInt(40)];
            for (int i = 0; i < a.length; i++) a[i] = rng.nextInt(9)-4;
            Arrays.sort(a);
            int[] before = a.clone();
            for (int target = -5; target <= 5; target++) {
                int count = 0;
                for (int value : a) if (value == target) count++;
                Checks.eq(count > a.length/2, s.isMajorityElement(a, target));
            }
            Checks.eq(before, a);
        }
        return Checks.count;
    }
}
