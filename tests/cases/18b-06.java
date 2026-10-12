import java.util.*;
class Case {
    public static int run() {
        Solution s = new Solution();
        Checks.eq(10, s.depthSum(Arrays.asList(
            new TestNested(Arrays.asList(new TestNested(1),new TestNested(1))), new TestNested(2),
            new TestNested(Arrays.asList(new TestNested(1),new TestNested(1))))));
        Checks.eq(27, s.depthSum(Arrays.asList(new TestNested(1),
            new TestNested(Arrays.asList(new TestNested(4),new TestNested(Arrays.asList(new TestNested(6))))))));
        Checks.eq(-1, s.depthSum(Arrays.asList(new TestNested(-1))));
        Checks.eq(0, s.depthSum(Collections.emptyList()));
        return Checks.count;
    }
}
