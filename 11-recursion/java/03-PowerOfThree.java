/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd recurse on n / 3 only when n is positive and divisible by three.
 * - One is the successful base case; an iterative version makes the same reductions without
 *   stack frames.
 * Time complexity: O(log(n + 1)) for positive n, for both versions.
 * Space complexity: Primary recursive: O(log(n + 1)) stack; iterative: O(1).
 */

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
