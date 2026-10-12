/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd accumulate a number, sign, and result for the current parentheses level.
 * - At an opening parenthesis I'd save the outer context; at a closing one I'd combine the
 *   completed inner result with it.
 * Time complexity: O(n) for expression length n.
 * Space complexity: O(d) auxiliary stack for nesting depth d; O(n) worst case.
 */

// 224. Basic Calculator
// https://leetcode.com/problems/basic-calculator/
// Solution; handles +, -, parentheses, spaces, and valid unary minus.
import java.util.*;

class Solution {
    public int calculate(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        int result = 0, number = 0, sign = 1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                number = number * 10 + c - '0';
            } else if (c == '+' || c == '-') {
                result += sign * number;
                number = 0;
                sign = c == '+' ? 1 : -1;
            } else if (c == '(') {
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;
            } else if (c == ')') {
                result += sign * number;
                number = 0;
                result = stack.pop() * result + stack.pop();
            }
        }
        return result + sign * number;
    }
}
