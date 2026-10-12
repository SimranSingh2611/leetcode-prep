/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd expand around every character and every gap as a palindrome center.
 * - Each successful expansion counts one palindrome, covering both odd and even lengths.
 * Time complexity: O(n^2) worst case for string length n.
 * Space complexity: O(1) auxiliary space.
 */

// O(n^2) time and O(1) space.
class Solution {
    public int countSubstrings(String s) {
        int count = 0;
        for (int center = 0; center < 2 * s.length() - 1; center++) {
            int left = center / 2;
            int right = left + center % 2;
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                count++;
                left--;
                right++;
            }
        }
        return count;
    }
}

