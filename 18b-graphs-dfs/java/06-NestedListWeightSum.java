// 339. Nested List Weight Sum
// https://leetcode.com/problems/nested-list-weight-sum/
// Solution preserves the nested input. LeetCode supplies NestedInteger; this Premium screenshot
// problem is a recursive-DFS bridge, not an explicit adjacency-list graph.
import java.util.*;

class Solution {
    public int depthSum(List<NestedInteger> nestedList) {
        return sum(nestedList, 1);
    }

    private int sum(List<NestedInteger> list, int depth) {
        int total = 0;
        for (NestedInteger value : list) {
            if (value.isInteger()) {
                total += value.getInteger() * depth;
            } else {
                total += sum(value.getList(), depth + 1);
            }
        }
        return total;
    }
}
