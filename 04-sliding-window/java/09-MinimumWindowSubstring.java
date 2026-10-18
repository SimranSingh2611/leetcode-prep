/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd count the required characters, expand until they are all covered, then shrink to the
 *   smallest valid window.
 * - When shrinking removes a required occurrence, I'd resume expansion; storing only the best
 *   endpoints delays output copying.
 * Time complexity: O(n + m) for source length n and target length m.
 * Space complexity: O(1) ASCII counter state for the primary solution, plus up to O(n) returned
 *   substring storage; a generalized map uses O(A) counters.
 */

// 76. Minimum Window Substring
// https://leetcode.com/problems/minimum-window-substring/
// NeetCode 150: https://neetcode.io/problems/minimum-window-with-characters/question?list=neetcode150
// O(|s| + |t|) time and O(1) space for the problem's ASCII character set.
class Solution {
    public String minWindow(String s, String t) {
        if (t.length() > s.length()) {
            return "";
        }

        int[] needed = new int[128];
        for (int i = 0; i < t.length(); i++) {
            needed[t.charAt(i)]++;
        }

        int missing = t.length();
        int left = 0;
        int bestStart = 0;
        int bestLength = Integer.MAX_VALUE;

        for (int right = 0; right < s.length(); right++) {
            char entering = s.charAt(right);
            if (needed[entering] > 0) {
                missing--;
            }
            needed[entering]--;

            while (missing == 0) {
                int length = right - left + 1;
                if (length < bestLength) {
                    bestLength = length;
                    bestStart = left;
                }

                char leaving = s.charAt(left++);
                needed[leaving]++;
                if (needed[leaving] > 0) {
                    missing++;
                }
            }
        }

        return bestLength == Integer.MAX_VALUE
                ? ""
                : s.substring(bestStart, bestStart + bestLength);
    }
}

