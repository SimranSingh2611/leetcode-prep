// 1545. Find Kth Bit in Nth Binary String
// https://leetcode.com/problems/find-kth-bit-in-nth-binary-string/
// Solution; uses the statement's n<=20 bound for int shifts.
import java.util.*;

class Solution {
    public char findKthBit(int n, int k) {
        if (n == 1) {
            return '0';
        }
        int middle = 1 << (n - 1);
        if (k == middle) {
            return '1';
        }
        if (k < middle) {
            return findKthBit(n - 1, k);
        }
        char bit = findKthBit(n - 1, 2 * middle - k);
        return bit == '0' ? '1' : '0';
    }
}
