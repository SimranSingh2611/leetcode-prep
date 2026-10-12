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

