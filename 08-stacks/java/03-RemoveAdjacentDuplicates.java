// 1047. Remove All Adjacent Duplicates In String
// https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/
// Solution; input preserved.
import java.util.*;

class Solution {
    public String removeDuplicates(String s) {
        StringBuilder stack = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int n = stack.length();
            if (n > 0 && stack.charAt(n - 1) == c) {
                stack.setLength(n - 1);
            } else {
                stack.append(c);
            }
        }
        return stack.toString();
    }
}
