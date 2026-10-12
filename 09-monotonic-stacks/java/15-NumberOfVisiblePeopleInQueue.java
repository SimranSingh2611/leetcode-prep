// 1944. Number of Visible People in a Queue
// https://leetcode.com/problems/number-of-visible-people-in-a-queue/
// Guide template 3, merged next >= and previous >: process inside AND after while.
// Relies on the problem's distinct heights. O(n) time/space; input preserved.
import java.util.*;

class Solution {
    public int[] canSeePersonsCount(int[] heights) {
        int[] answer = new int[heights.length];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] <= heights[i]) {
                // Each popped person sees i as their final (taller) visible person.
                answer[stack.pop()]++;
            }
            if (!stack.isEmpty()) {
                // Only the nearest remaining taller person sees i; that person
                // blocks i from everyone farther left still on the stack.
                answer[stack.peek()]++;
            }
            stack.push(i);
        }
        return answer;
    }
}
