// 394. Decode String
// https://leetcode.com/problems/decode-string/
// Solution; iterative context stacks avoid recursive parsing depth.
import java.util.*;

class Solution {
    public String decodeString(String s) {
        Deque<Integer> repeats = new ArrayDeque<>();
        Deque<StringBuilder> prefixes = new ArrayDeque<>();
        StringBuilder current = new StringBuilder();
        int number = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                number = number * 10 + c - '0';
            } else if (c == '[') {
                repeats.push(number);
                prefixes.push(current);
                current = new StringBuilder();
                number = 0;
            } else if (c == ']') {
                StringBuilder outer = prefixes.pop();
                int count = repeats.pop();
                for (int j = 0; j < count; j++) {
                    outer.append(current);
                }
                current = outer;
            } else {
                current.append(c);
            }
        }
        return current.toString();
    }
}
