/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd scan two virtual copies of the array to cover wraparound.
 * - Only the first pass pushes indices; both passes can resolve a pending index with a larger
 *   value.
 * Time complexity: O(n) amortized overall for n values.
 * Space complexity: O(n) auxiliary stack, plus O(n) output.
 */

// 503. Next Greater Element II
// https://leetcode.com/problems/next-greater-element-ii/
// Next Greater adaptation: forward circular scan, pop <, record the resolving value.
// Circular adaptation: scan twice, but push each original index only once.
// O(n) time and O(n) auxiliary space; input preserved.
import java.util.*;

class Solution {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] out = new int[n];
        Arrays.fill(out, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 2 * n; i++) {
            int index = i % n;
            while (!stack.isEmpty() && nums[stack.peek()] < nums[index]) {
                int j = stack.pop();
                out[j] = nums[index]; // Current virtual position i answers waiting j.
            }
            if (i < n) {
                // Second pass only resolves indices still waiting for wraparound.
                stack.push(i);
            }
        }
        return out;
    }
}
