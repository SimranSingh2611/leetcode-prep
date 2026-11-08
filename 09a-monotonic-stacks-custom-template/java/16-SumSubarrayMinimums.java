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
// Two passes: previous strictly smaller (reverse, pop >), next smaller-or-equal
// (forward, pop >=). Asymmetric equality assigns ties to the rightmost minimum.
// O(n) time and O(n) auxiliary space; input preserved.
import java.util.*;

class Solution {
    public int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        int[] left = new int[n], right = new int[n];
        Arrays.fill(left, -1);
        Arrays.fill(right, n);
        Stack<Integer> stack = new Stack<>();

        // Previous strictly smaller: equal values cannot resolve a waiting index.
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
                int j = stack.pop();
                left[j] = i;
            }
            stack.push(i);
        }

        stack.clear();
        // Next smaller-or-equal: an equal value DOES resolve a waiting index.
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                int j = stack.pop();
                right[j] = i;
            }
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
