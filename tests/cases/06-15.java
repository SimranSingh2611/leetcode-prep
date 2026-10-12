import java.util.*;
class Case {
    public static int run() {
        Solution s = new Solution();
        Checks.eq('c', s.nextGreatestLetter(new char[]{'c','f','j'}, 'a'));
        Checks.eq('f', s.nextGreatestLetter(new char[]{'c','f','j'}, 'c'));
        Checks.eq('x', s.nextGreatestLetter(new char[]{'x','x','y','y'}, 'z'));
        Checks.eq('b', s.nextGreatestLetter(new char[]{'a','a','b','b'}, 'a'));
        Random rng = new Random(6744);
        for (int t = 0; t < 300; t++) {
            char[] a = new char[2+rng.nextInt(60)];
            for (int i = 0; i < a.length; i++) a[i] = (char) ('a'+rng.nextInt(26));
            Arrays.sort(a);
            char[] before = a.clone();
            for (char target = 'a'; target <= 'z'; target++) {
                char expected = a[0];
                for (char letter : a) {
                    if (letter > target) {
                        expected = letter;
                        break;
                    }
                }
                Checks.eq(expected, s.nextGreatestLetter(a, target));
            }
            Checks.eq(true, Arrays.equals(before,a));
        }
        return Checks.count;
    }
}
