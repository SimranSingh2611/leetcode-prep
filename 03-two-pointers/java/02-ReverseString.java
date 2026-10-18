/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd swap the two endpoint characters and move inward.
 * - The outer positions are final after each swap, so the array can be reversed in place.
 * Time complexity: O(n) for n characters.
 * Space complexity: O(1) auxiliary space.
 */

// 344. Reverse String
// https://leetcode.com/problems/reverse-string/
// O(n) time and O(1) space.
class Solution {
    public void reverseString(char[] s) {
        int left = 0;
        int right = s.length - 1;
        while (left < right) {
            char temporary = s[left];
            s[left] = s[right];
            s[right] = temporary;
            left++;
            right--;
        }
    }
}

