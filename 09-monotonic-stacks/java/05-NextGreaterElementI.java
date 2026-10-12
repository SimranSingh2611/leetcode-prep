/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd scan the second array with a stack of values awaiting their next greater value.
 * - Each larger arrival resolves smaller tops, and a map answers the first array's queries
 *   afterward.
 * Time complexity: O(n + m) expected for second-array length n and first-array length m.
 * Space complexity: O(n) auxiliary stack/map storage, plus O(m) output.
 */

// 496. Next Greater Element I
// https://leetcode.com/problems/next-greater-element-i/
// Guide template 1, next strictly greater: pop <; non-increasing values.
// Distinct values allow a value-keyed answer map; the stack stores indices.
// O(n+m) expected time and O(n) auxiliary space; inputs preserved.
import java.util.*;

class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {

        int[] out = new int[nums1.length];
        int n = nums2.length;

        Stack<Integer> s = new Stack<>();
        Map<Integer, Integer> nums2ToGreaterIndex = new HashMap<>();

        for(int i=0; i<n; i++) {
            while(!s.isEmpty() && nums2[s.peek()] < nums2[i]) {
                int top = s.pop();
                nums2ToGreaterIndex.put(nums2[top], nums2[i]);
            }
            s.push(i);
        }

        for(int i=0; i<out.length; i++) {
            out[i] = nums2ToGreaterIndex.getOrDefault(nums1[i], -1);
        }
        return out;
    }
}
