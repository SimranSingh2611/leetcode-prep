/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd discard earlier buildings no taller than each new building to their right.
 * - Those buildings lose their ocean view; surviving indices form the answer in original order.
 * Time complexity: O(n) amortized overall for n buildings.
 * Space complexity: O(n) auxiliary stack, plus up to O(n) output.
 */

// 1762. Buildings With an Ocean View
// https://leetcode.com/problems/buildings-with-an-ocean-view/
// Guide template 1, next greater-or-equal: pop <=; strictly decreasing heights.
// LeetCode Premium. O(n) time and O(n) auxiliary space; input preserved.
import java.util.*;

class Solution {
    public int[] findBuildings(int[] heights) {
        // next greater -> ans is the one that does not have next greater

        List<Integer> out1 = new ArrayList<>();
        Stack<Integer> s = new Stack<>();
        
        int n = heights.length;
        int[] out = new int[n];
        Arrays.fill(out, -1);

        for(int i=0;i < n; i++) {
            while (!s.isEmpty() && heights[s.peek()] <= heights[i]) {
                int ans = s.pop();
                out[ans] = i;
            }
            s.add(i);
        }

        for(int i=0; i<n; i++)
            if (out[i] == -1)
                out1.add(i);

        int[] ans = new int[out1.size()];
        for(int i=0; i<out1.size(); i++)
            ans[i] = out1.get(i);
        
        return ans;

    }
}
