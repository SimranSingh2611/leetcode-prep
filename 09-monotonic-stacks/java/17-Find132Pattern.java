/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd track prefix minima and candidate peaks in a decreasing stack.
 * - After removing peaks no greater than the current value, a surviving peak with an earlier
 *   smaller prefix forms the required 1-3-2 order.
 * Time complexity: O(n) amortized overall for n values.
 * Space complexity: O(n) auxiliary prefix-minimum and stack space.
 */

// 456. 132 Pattern
// https://leetcode.com/problems/132-pattern/
// Guide template 2, previous strictly greater: pop <=; inspect top AFTER popping.
// O(n) time and O(n) auxiliary space; input preserved.
import java.util.*;

class Solution {
    public boolean find132pattern(int[] nums) {
        int[] prefixMin = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            prefixMin[i] = i == 0 ? nums[i] : Math.min(prefixMin[i - 1], nums[i]);
            while (!stack.isEmpty() && nums[stack.peek()] <= nums[i]) {
                stack.pop();
            }
            if (!stack.isEmpty()) {
                int peak = stack.peek(); // Previous greater index supplies the "3".
                // Current nums[i] supplies "2". A strictly smaller prefix value
                // supplies "1" and must precede peak because nums[peak] > nums[i].
                if (prefixMin[peak] < nums[i]) {
                    return true;
                }
            }
            stack.push(i);
        }
        return false;
    }
}
