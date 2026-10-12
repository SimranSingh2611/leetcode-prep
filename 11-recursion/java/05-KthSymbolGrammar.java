/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd trace the requested symbol back to its parent in the preceding row.
 * - Odd positions copy the parent and even positions flip it, so I can avoid constructing the
 *   rows.
 * Time complexity: O(n) for row number n.
 * Space complexity: O(n) recursive stack.
 */

// 779. K-th Symbol in Grammar
// https://leetcode.com/problems/k-th-symbol-in-grammar/
// Solution; k is a valid 1-based position.
import java.util.*;

class Solution {
    public int kthGrammar(int n, int k) {
        if (n == 1) {
            return 0;
        }
        int parent = kthGrammar(n - 1, (k + 1) / 2);
        return k % 2 == 1 ? parent : 1 - parent;
    }
}
