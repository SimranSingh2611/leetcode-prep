// 42. Trapping Rain Water
// https://leetcode.com/problems/trapping-rain-water/
// Guide template 3, merged greater boundaries: pop <=; strictly decreasing heights.
// O(n) time and O(n) auxiliary space; input preserved.
import java.util.*;

class Solution {
    public int trap(int[] height) {
        Deque<Integer> stack = new ArrayDeque<>();
        int water = 0;
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[stack.peek()] <= height[i]) {
                int bottom = stack.pop();
                // Current i is the next >= boundary; the surviving top is the
                // previous > boundary. Without a left wall there is no basin.
                if (!stack.isEmpty()) {
                    int left = stack.peek();
                    int depth = Math.min(height[left], height[i]) - height[bottom];
                    water += depth * (i - left - 1);
                }
            }
            stack.push(i);
        }
        return water;
    }
}
