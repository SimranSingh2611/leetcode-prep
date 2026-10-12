// 402. Remove K Digits
// https://leetcode.com/problems/remove-k-digits/
// Guide template 4 adaptation: pop > while deletion budget remains, then push.
// Store digits rather than indices because the retained sequence is the answer.
// The stack is non-decreasing only while k > 0; after that, all digits must stay.
// O(n) time and O(n) auxiliary space; input String is immutable.
import java.util.*;

class Solution {
    public String removeKdigits(String num, int k) {
        char[] stack = new char[num.length()];
        int size = 0;
        for (int i = 0; i < num.length(); i++) {
            char c = num.charAt(i);
            while (size > 0 && k > 0 && stack[size - 1] > c) {
                // Greedy processing INSIDE while: remove a worse earlier digit.
                size--;
                k--;
            }
            stack[size++] = c;
        }
        size -= k; // Any remaining budget removes the largest trailing digits.
        int first = 0;
        while (first < size && stack[first] == '0') {
            first++;
        }
        return first == size ? "0" : new String(stack, first, size - first);
    }
}
