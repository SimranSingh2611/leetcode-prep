// Next Smaller — standalone template exercise (no LeetCode problem ID).
// https://leetcode.com/discuss/post/2347639/a-comprehensive-guide-and-template-for-m-irii/
// Guide template 4: pop >; non-decreasing values from bottom to top.
// Return nearest strictly smaller indices to the right, or -1 if absent.
// O(n) time and O(n) auxiliary space; input preserved. Equal values do not qualify.
import java.util.*;

class Solution {
    public int[] nextSmallerIndexes(int[] values) {
        int[] answer = new int[values.length];
        Arrays.fill(answer, -1);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < values.length; i++) {
            while (!stack.isEmpty() && values[stack.peek()] > values[i]) {
                // Resolve INSIDE while: i is the first strictly smaller index to the right.
                answer[stack.pop()] = i;
            }
            stack.push(i);
        }
        return answer;
    }
}
