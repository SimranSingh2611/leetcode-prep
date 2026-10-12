/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd keep the currently valid scores on a stack.
 * - Cancel, double, and sum operations depend only on the latest scores, so the stack directly
 *   models the history.
 * Time complexity: O(n) for n operations, with bounded-length score tokens.
 * Space complexity: O(n) auxiliary score-stack space.
 */

// 682. Baseball Game
// https://leetcode.com/problems/baseball-game/
// Solution; input preserved.
import java.util.*;

class Solution {
    public int calPoints(String[] operations) {
        Deque<Integer> scores = new ArrayDeque<>();
        for (String op : operations) {
            if (op.equals("C")) {
                scores.pop();
            } else if (op.equals("D")) {
                scores.push(2 * scores.peek());
            } else if (op.equals("+")) {
                int last = scores.pop(), sum = last + scores.peek();
                scores.push(last);
                scores.push(sum);
            } else {
                scores.push(Integer.parseInt(op));
            }
        }
        int total = 0;
        for (int score : scores) {
            total += score;
        }
        return total;
    }
}
