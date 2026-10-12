/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd sort children by greed and cookies by size, then serve the least demanding unsatisfied
 *   child first.
 * - Using the smallest adequate cookie preserves larger cookies for later children, making the
 *   greedy choice safe.
 * Time complexity: O(n log(n + 1) + m log(m + 1)) for n children and m cookies.
 * Space complexity: O(n + m) auxiliary cloned-array and sorting storage.
 */

// 455. Assign Cookies
// https://leetcode.com/problems/assign-cookies/
// Solution preserves both arrays; sorting them in place would remove the clone storage.
import java.util.*;

class Solution {
    public int findContentChildren(int[] g, int[] s) {
        int[] greed = g.clone(), cookies = s.clone();
        Arrays.sort(greed);
        Arrays.sort(cookies);
        int child = 0;
        for (int cookie : cookies) {
            if (child < greed.length && cookie >= greed[child]) {
                child++;
            }
        }
        return child;
    }
}
