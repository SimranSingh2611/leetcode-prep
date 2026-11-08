/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd find previous-greater indices with a reverse scan, then compute prefix minima.
 * - For each possible "2", its previous greater supplies "3"; a smaller prefix supplies "1".
 * Time complexity: O(n) amortized overall for n values.
 * Space complexity: O(n) auxiliary previous-greater/prefix-minimum arrays and stack space.
 */

// 456. 132 Pattern
// https://leetcode.com/problems/132-pattern/
// Previous Greater: reverse scan, pop <, assign the popped index's answer inside while.
// Then combine that boundary with prefix minima to test the 1-3-2 ordering.
// O(n) time and O(n) auxiliary space; input preserved.
import java.util.*;

class Solution {
    public boolean find132pattern(int[] nums) {
        int n = nums.length;
        int[] previousGreater = new int[n], prefixMin = new int[n];
        Arrays.fill(previousGreater, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                int j = stack.pop();
                previousGreater[j] = i;
            }
            stack.push(i);
        }

        for (int i = 0; i < n; i++) {
            prefixMin[i] = i == 0 ? nums[i] : Math.min(prefixMin[i - 1], nums[i]);
        }
        for (int i = 0; i < n; i++) {
            int peak = previousGreater[i]; // nums[i] is "2"; nums[peak] is "3".
            // Since nums[peak] > nums[i], a smaller prefix minimum must precede peak.
            if (peak != -1 && prefixMin[peak] < nums[i]) {
                return true;
            }
        }
        return false;
    }
}
