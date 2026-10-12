/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - A permutation must occupy a window exactly as long as the first string.
 * - I'd slide that fixed window over the second string and compare its 26-letter counts with
 *   the required counts.
 * Time complexity: O(n + m) for the two string lengths.
 * Space complexity: O(1) for fixed lowercase-letter counters.
 */

// O(|s1| + |s2|) time and O(1) space for lowercase English letters.
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) {
            return false;
        }

        int[] needed = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            needed[s1.charAt(i) - 'a']++;
        }

        int missing = s1.length();
        for (int right = 0; right < s2.length(); right++) {
            int entering = s2.charAt(right) - 'a';
            if (needed[entering] > 0) {
                missing--;
            }
            needed[entering]--;

            if (right >= s1.length()) {
                int leaving = s2.charAt(right - s1.length()) - 'a';
                if (needed[leaving] >= 0) {
                    missing++;
                }
                needed[leaving]++;
            }

            if (missing == 0) {
                return true;
            }
        }
        return false;
    }
}

