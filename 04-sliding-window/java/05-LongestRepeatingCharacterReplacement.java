// O(n) time and O(1) space for uppercase English letters.
class Solution {
    public int characterReplacement(String s, int k) {
        int[] frequency = new int[26];
        int left = 0;
        int highestFrequency = 0;
        int longest = 0;

        for (int right = 0; right < s.length(); right++) {
            int index = s.charAt(right) - 'A';
            highestFrequency = Math.max(highestFrequency, ++frequency[index]);

            while (right - left + 1 - highestFrequency > k) {
                frequency[s.charAt(left) - 'A']--;
                left++;
            }
            longest = Math.max(longest, right - left + 1);
        }
        return longest;
    }
}

