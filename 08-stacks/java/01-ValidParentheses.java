/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd push the expected closing bracket for each opener.
 * - Every closer must match the stack top, and the stack must finish empty; counts alone cannot
 *   enforce nesting.
 * Time complexity: O(n) for n characters.
 * Space complexity: O(n) auxiliary stack space.
 */

// 20. Valid Parentheses
// https://leetcode.com/problems/valid-parentheses/
// NeetCode 150: https://neetcode.io/problems/validate-parentheses/question?list=neetcode150
// Solution; input preserved.
import java.util.*;

class Solution {
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(')');
            } else if (c == '[') {
                stack.push(']');
            } else if (c == '{') {
                stack.push('}');
            } else if (stack.isEmpty() || stack.pop() != c) {
                return false;
            }
        }
        return stack.isEmpty();
    }
}
