/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd compare characters from both ends, skipping non-alphanumeric characters.
 * - Each case-insensitive match lets both pointers move inward; any mismatch settles the
 *   answer.
 * Time complexity: O(n) for a string of length n.
 * Space complexity: O(1) auxiliary space.
 */

// 125. Valid Palindrome
// https://leetcode.com/problems/valid-palindrome/
// NeetCode 150: https://neetcode.io/problems/is-palindrome/question?list=neetcode150
// O(n) time and O(1) space.
class Solution {
    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }
            if (Character.toLowerCase(s.charAt(left))
                    != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}

