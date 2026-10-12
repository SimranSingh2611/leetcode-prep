import java.util.*;
class Case {
    public static int run() {
        Solution s = new Solution();
        double[] result = s.calcEquation(Arrays.asList(Arrays.asList("a","b"),Arrays.asList("b","c")),new double[]{2,3},
        Arrays.asList(Arrays.asList("a","c"),Arrays.asList("b","a"),Arrays.asList("a","e"),Arrays.asList("a","a"),Arrays.asList("x","x")));
        double[] expected = {6,0.5,-1,1,-1};
        for (int i = 0; i < expected.length; i++) { Checks.close(expected[i],result[i]); }
        Checks.close(-1,s.calcEquation(Arrays.asList(Arrays.asList("a","b"),Arrays.asList("c","d")),new double[]{2,3},
        Arrays.asList(Arrays.asList("a","d")))[0]);
        return Checks.count;
    }
}
