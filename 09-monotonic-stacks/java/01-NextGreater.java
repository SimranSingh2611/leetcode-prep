/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd keep unresolved indices in non-increasing value order.
 * - A larger incoming value resolves each smaller top as its next greater answer; equal values
 *   stay unresolved.
 * Time complexity: O(n) amortized overall for n values.
 * Space complexity: O(n) auxiliary stack, plus O(n) output indices.
 */

// Next Greater — standalone template exercise (no LeetCode problem ID).
// https://leetcode.com/discuss/post/2347639/a-comprehensive-guide-and-template-for-m-irii/
// Guide template 1: pop <; non-increasing values from bottom to top.
// Return nearest strictly greater indices to the right, or -1 if absent.
// O(n) time and O(n) auxiliary space; input preserved. Equal values do not qualify.
import java.util.*;

class Solution {
    public int[] nextGreaterIndexes(int[] nums) {
        int[] answer = new int[nums.length];
        Arrays.fill(answer, -1);
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] < nums[i]) {
                // Resolve INSIDE while: i is the first strictly greater index to the right.
                answer[stack.pop()] = i;
            }
            stack.push(i);
        }
        return answer;
    }
}
