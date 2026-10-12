/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd process path components with a stack: skip empty names and dots, and let double dots
 *   remove the previous component.
 * - Every other name is literal, then I'd join the surviving components under the root slash.
 * Time complexity: O(n) for path length n.
 * Space complexity: O(n) auxiliary split/stack storage and up to O(n) output.
 */

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
