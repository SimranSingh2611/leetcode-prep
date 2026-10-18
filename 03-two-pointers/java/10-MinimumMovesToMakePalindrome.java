/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd match the leftmost character with its rightmost available partner and bubble that
 *   partner to the right edge.
 * - If no partner exists, that character belongs in the center, so I'd move it inward and
 *   retry.
 * Time complexity: O(n^2) worst case for string length n.
 * Space complexity: O(n) for Java's mutable character-array copy; the swap logic itself uses
 *   O(1) state.
 */

// 2193. Minimum Number of Moves to Make Palindrome
// https://leetcode.com/problems/minimum-number-of-moves-to-make-palindrome/
// O(n^2) time and O(n) space for Java's mutable character array.
class Solution {
    public int minMovesToMakePalindrome(String s) {
        char[] characters = s.toCharArray();
        int left = 0;
        int right = characters.length - 1;
        int moves = 0;

        while (left < right) {
            int match = right;
            while (match > left && characters[match] != characters[left]) {
                match--;
            }

            if (match == left) {
                swap(characters, left, left + 1);
                moves++;
            } else {
                while (match < right) {
                    swap(characters, match, match + 1);
                    match++;
                    moves++;
                }
                left++;
                right--;
            }
        }
        return moves;
    }

    private void swap(char[] values, int first, int second) {
        char temporary = values[first];
        values[first] = values[second];
        values[second] = temporary;
    }
}

