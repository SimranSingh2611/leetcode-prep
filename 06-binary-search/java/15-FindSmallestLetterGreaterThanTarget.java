/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd find the first letter strictly greater than the target.
 * - If that upper bound is the virtual endpoint n, I'd return the first letter for wraparound.
 * Time complexity: O(log(n + 1)) for n letters.
 * Space complexity: O(1) auxiliary space.
 */

// 744. Find Smallest Letter Greater Than Target
// https://leetcode.com/problems/find-smallest-letter-greater-than-target/
/*
 * TYPE: MINIMIZATION -- first index with letters[i] > target.
 * BOUNDS: lo=-1 (false), hi=n (virtual true); wrap to letters[0] if hi=n.
 */
// O(log(n + 1)) time, O(1) auxiliary space.
class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int lo = -1;
        int hi = letters.length;
        while (lo + 1 < hi) {
            int mid = lo + (int) (((long) hi - lo) / 2);
            if (letters[mid] > target) {
                hi = mid;
            } else {
                lo = mid;
            }
        }
        return hi == letters.length ? letters[0] : letters[hi];
    }
}
