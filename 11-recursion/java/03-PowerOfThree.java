// 326. Power of Three
// https://leetcode.com/problems/power-of-three/
// Solution recursive; IterativeSolution removes stack space.
import java.util.*;

class Solution {
    public boolean isPowerOfThree(int n) {
        if (n <= 0) {
            return false;
        }
        if (n == 1) {
            return true;
        }
        return n % 3 == 0 && isPowerOfThree(n / 3);
    }
}

class IterativeSolution {
    public boolean isPowerOfThree(int n) {
        if (n <= 0) {
            return false;
        }
        while (n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }
}
