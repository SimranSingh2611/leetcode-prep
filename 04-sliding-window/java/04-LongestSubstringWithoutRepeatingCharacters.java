/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd store each character's latest index while expanding the right edge.
 * - A repeat inside the window moves the left edge just beyond its previous occurrence, and the
 *   left edge never moves backward.
 * Time complexity: O(n) for string length n.
 * Space complexity: O(1) for the primary 128-entry ASCII array; a generalized map uses O(A) for
 *   A distinct characters.
 */

import java.util.Arrays;

// O(n) time and O(1) space for the problem's ASCII character set.
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int[] lastIndex = new int[128];
        Arrays.fill(lastIndex, -1);

        int left = 0;
        int longest = 0;
        for (int right = 0; right < s.length(); right++) {
            char character = s.charAt(right);
            left = Math.max(left, lastIndex[character] + 1);
            lastIndex[character] = right;
            longest = Math.max(longest, right - left + 1);
        }
        return longest;
    }
}

