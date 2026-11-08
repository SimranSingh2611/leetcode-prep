/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd scan the second array with indices awaiting their next greater value.
 * - Each larger arrival resolves smaller tops, and a map answers the first array's queries
 *   afterward.
 * Time complexity: O(n + m) expected for second-array length n and first-array length m.
 * Space complexity: O(n) auxiliary stack/map storage, plus O(m) output.
 */

// 496. Next Greater Element I
// https://leetcode.com/problems/next-greater-element-i/
// Next Greater adaptation: forward scan, pop <, record a value instead of an index.
// Distinct values allow a value-keyed answer map; the stack stores indices.
// O(n+m) expected time and O(n) auxiliary space; inputs preserved.
import java.util.*;

class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {

        int[] out = new int[nums1.length];
        int n = nums2.length;

        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> nextGreaterValue = new HashMap<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums2[stack.peek()] < nums2[i]) {
                int j = stack.pop();
                nextGreaterValue.put(nums2[j], nums2[i]); // Current i answers waiting j.
            }
            stack.push(i);
        }

        for (int i = 0; i < out.length; i++) {
            out[i] = nextGreaterValue.getOrDefault(nums1[i], -1);
        }
        return out;
    }
}
