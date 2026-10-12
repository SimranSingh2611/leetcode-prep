/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd save the outer text and repeat count whenever a bracket starts a nested segment.
 * - Closing the bracket expands that segment into its saved parent; nested copying needs to be
 *   included in the runtime.
 * Time complexity: O(n + X), where n is encoded length and X is the sum of intermediate
 *   expansion lengths; a loose bound is O(n(L + 1)) for decoded length L.
 * Space complexity: O(n + L) peak stack/builder/output storage.
 */

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
