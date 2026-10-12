/*
 * Interview quick notes — primary solution:
 * Talking points:
 * - I'd reject nonpositive values and repeatedly divide even values by two.
 * - Reaching one proves success; an odd value above one proves failure, and the bitwise follow-
 *   up checks for one set bit.
 * Time complexity: Recursive: O(log(n + 1)) for positive n; bitwise alternative: O(1) fixed-
 *   word operations.
 * Space complexity: Recursive: O(log(n + 1)) stack; bitwise: O(1).
 */

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
