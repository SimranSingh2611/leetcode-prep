// 231. Power of Two
// https://leetcode.com/problems/power-of-two/
// Solution teaches recursion; BitwiseSolution is the optimized alternative.
import java.util.*;

class Solution {
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }
        if (n == 1) {
            return true;
        }
        return n % 2 == 0 && isPowerOfTwo(n / 2);
    }
}

class BitwiseSolution {
    public boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }
}
