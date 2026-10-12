// 71. Simplify Path
// https://leetcode.com/problems/simplify-path/
// Solution; Unix absolute-path rules, not filesystem access.
import java.util.*;

class Solution {
    public String simplifyPath(String path) {
        Deque<String> parts = new ArrayDeque<>();
        for (String part : path.split("/")) {
            if (part.isEmpty() || part.equals(".")) {
                continue;
            }
            if (part.equals("..")) {
                if (!parts.isEmpty()) {
                    parts.removeLast();
                }
            } else {
                parts.addLast(part);
            }
        }
        return "/" + String.join("/", parts);
    }
}
