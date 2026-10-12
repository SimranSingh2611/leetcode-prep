/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd push numbers and apply each operator to the top two operands.
 * - I'd pop the right operand first so subtraction and division retain their order.
 * Time complexity: O(n) for n bounded-length tokens.
 * Space complexity: O(n) auxiliary operand-stack space.
 */

// 150. Evaluate Reverse Polish Notation
// https://leetcode.com/problems/evaluate-reverse-polish-notation/
// Solution; valid RPN input assumed.
import java.util.*;

class Solution {
    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (String token : tokens) {
            if (token.length() == 1 && "+-*/".contains(token)) {
                int right = stack.pop(), left = stack.pop(), value;
                switch (token.charAt(0)) {
                    case '+':
                        value = left + right;
                        break;
                    case '-':
                        value = left - right;
                        break;
                    case '*':
                        value = left * right;
                        break;
                    default:
                        value = left / right;
                }
                stack.push(value);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }
}
