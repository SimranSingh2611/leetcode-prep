// 456. 132 Pattern
// https://leetcode.com/problems/132-pattern/
// Guide template 2, previous strictly greater: pop <=; inspect top AFTER popping.
// O(n) time and O(n) auxiliary space; input preserved.
import java.util.*;

class Solution {
    public boolean find132pattern(int[] nums) {
        int[] prefixMin = new int[nums.length];
        Deque<Integer> stack = new ArrayDeque<>();
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
