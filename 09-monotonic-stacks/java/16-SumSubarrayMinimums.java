/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd count how many subarrays choose each value as their minimum using its left and right
 *   boundaries.
 * - One boundary must be strict and the other non-strict so equal minima are assigned exactly
 *   once.
 * Time complexity: O(n) amortized overall for n values.
 * Space complexity: O(n) auxiliary boundary/stack space.
 */

// 907. Sum of Subarray Minimums
// https://leetcode.com/problems/sum-of-subarray-minimums/
// Guide template 6, merged next <= and previous <: pop >=; strictly increasing values.
// Both boundaries come from one left-to-right pass; ties belong to the rightmost minimum.
// O(n) time and O(n) auxiliary space; input preserved.
import java.util.*;

class Solution {
    public int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        int[] left = new int[n], right = new int[n];
        Arrays.fill(right, n);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                // INSIDE while: i is the popped index's next smaller-or-equal.
                right[stack.pop()] = i;
            }
            // AFTER while: top is the current index's previous strictly smaller.
            left[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }
        long answer = 0, mod = 1_000_000_007L;
        for (int i = 0; i < n; i++) {
            // Asymmetric boundaries count equal-minimum subarrays exactly once.
            answer = (answer + (long) arr[i] * (i - left[i]) * (right[i] - i)) % mod;
        }
        return (int) answer;
    }
}
