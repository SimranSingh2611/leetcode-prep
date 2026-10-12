// 503. Next Greater Element II
// https://leetcode.com/problems/next-greater-element-ii/
// Guide template 1, next strictly greater: pop <; non-increasing values.
// Circular adaptation: scan twice, but push each original index only once.
// O(n) time and O(n) auxiliary space; input preserved.
import java.util.*;

class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] out = new int[n];
        Arrays.fill(out, -1);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < 2 * n; i++) {
            int index = i % n;
            while (!stack.isEmpty() && nums[stack.peek()] < nums[index]) {
                // Resolve the popped index INSIDE while; equal values do not qualify.
                out[stack.pop()] = nums[index];
            }
            if (i < n) {
                // Second pass only resolves indices still waiting for wraparound.
                stack.push(i);
            }
        }
        return out;
    }
}
