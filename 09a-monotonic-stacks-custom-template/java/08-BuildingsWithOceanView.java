/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd find each building's next greater-or-equal blocker to its right.
 * - Buildings with no blocker have ocean views; collect their indices in original order.
 * Time complexity: O(n) amortized overall for n buildings.
 * Space complexity: O(n) auxiliary stack, plus up to O(n) output.
 */

// 1762. Buildings With an Ocean View
// https://leetcode.com/problems/buildings-with-an-ocean-view/
// Next Greater-or-Equal adaptation: forward scan, pop <=, record each building's blocker.
// LeetCode Premium. O(n) time and O(n) auxiliary space; input preserved.
import java.util.*;

class Solution {
    public int[] findBuildings(int[] heights) {
        int n = heights.length;
        int[] nextBlocker = new int[n];
        Arrays.fill(nextBlocker, -1);
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && heights[stack.peek()] <= heights[i]) {
                int j = stack.pop();
                nextBlocker[j] = i; // Equal-height buildings also block the view.
            }
            stack.push(i);
        }

        List<Integer> visible = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (nextBlocker[i] == -1) {
                visible.add(i);
            }
        }

        int[] answer = new int[visible.size()];
        for (int i = 0; i < visible.size(); i++) {
            answer[i] = visible.get(i);
        }
        return answer;
    }
}
