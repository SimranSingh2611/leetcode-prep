// 409. Longest Palindrome
// https://leetcode.com/problems/longest-palindrome/
// Solution uses a fixed ASCII frequency array; this is not longest palindromic substring or
// subsequence.
import java.util.*;

class Solution {
    public int longestPalindrome(String s) {
        int[] counts = new int[128];
        for (int i = 0; i < s.length(); i++) {
            counts[s.charAt(i)]++;
        }
        int length = 0;
        boolean odd = false;
        for (int count : counts) {
            length += (count / 2) * 2;
            odd |= (count % 2 == 1);
        }
        return length + (odd ? 1 : 0);
    }
}
